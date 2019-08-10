package com.example.enterc.practiceinter.Model.Helper;

import android.content.Context;
import android.text.Html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StringHelper {
    public static int ConvertStringToMillis(String time) {
        if (time == null || time.isEmpty())
            return -1;

        List<String> times = Arrays.asList(time.split(":"));
        int timeMillis = -1;
        if (times.size() == 3) { //string  "00:00:00"
            try {
                timeMillis = Integer.parseInt(times.get(0)) * 60 * 60 * 1000;
            } catch (NumberFormatException e) {
                timeMillis = 0;
            }

            try {
                timeMillis += Integer.parseInt(times.get(1)) * 60 * 1000;
            } catch (NumberFormatException e) {
                timeMillis += 0;
            }
            String tmp = times.get(2).replace(",", ".");

            try {
                timeMillis += Double.parseDouble(tmp.contains(" ") ? tmp.substring(0, tmp.indexOf(" ")) : tmp) * 1000;
            } catch (NumberFormatException e) {
                timeMillis += 0;
            }

        } else if (times.size() == 2) { //string  "00:00"
            try {
                timeMillis = Integer.parseInt(times.get(0)) * 60 * 1000;
            } catch (NumberFormatException e) {
                timeMillis = 0;
            }
            String tmp = times.get(1).replace(",", ".");
            try {
                timeMillis += Double.parseDouble(tmp.contains(" ") ? tmp.substring(0, tmp.indexOf(" ")) : tmp) * 1000;
            } catch (NumberFormatException e) {
                timeMillis += 0;
            }
        }
        return timeMillis;
    }

        private static final String tagRuby = "<ruby>%s<rt>%s</rt></ruby>";
        private static final int HIRAGANA_START = 0x3040;
        private static final int HIRAGANA_END = 0x309F;
        private static final int KATAKANA_START = 0x30A0;
        private static final int KATAKANA_END = 0x30FF;
        private static final int KATAKANA_PHONETIC_START = 0x31F0;
        private static final int KATAKANA_PHONETIC_END = 0x31FF;
        private static final int KANJI_START = 0x4e00;
        private static final int KANJI_END = 0x9faf;
        private static final int RARE_KANJI_START = 0x3400;
        private static final int RARE_KANJI_END = 0x4dbf;
        private static final int JAPANESE_STYLE_START = 0x3000;
        private static final int JAPANESE_STYLE_END = 0x303f;
//    private static final char SPACIAL[] = {'\\', '^', '$', '{', '}', '[', ']', '(', ')', '.', '*', '+', '?', '|', '<', '>', '&'};

        /*
        merge to enable furigana
    */
        public static String htlm2Furigana(String html) {
            html = html.replaceAll("<ruby>", "{");
            html = html.replaceAll("<rt>", ";");
            html = html.replaceAll("</rt></ruby>", "}");
            return Html.fromHtml(html).toString();
        }

        /*
            merge to disable furigana
         */
        public static String html2String(String html) {
            html = html.replaceAll("/</?span[^>]*>/g", "");
            html = html.replaceAll("<ruby>", "");
            html = html.replaceAll("</ruby>", "");
            html = html.replaceAll("<rt>[\\w-]+</rt>", "");
            return Html.fromHtml(html).toString();
        }

        public static String extractYoutubeId(String url) {
            String id = "undefined";
            try {
                String query = new URL(url).getQuery();
                if (query != null) {
                    String[] param = query.split("&");
                    for (String row : param) {
                        String[] param1 = row.split("=");
                        if (param1[0].equals("v")) {
                            id = param1[1];
                        }
                    }
                } else {
                    if (url.contains("embed") && url.contains("/")) {
                        id = url.substring(url.lastIndexOf("/") + 1);
                    } else if (url.contains("youtu.be") && url.contains("/")) {
                        if (url.lastIndexOf("/") < url.length())
                            id = url.substring(url.lastIndexOf("/") + 1);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return id;
        }

        public static String stringToFurigana(String value, String hira) {

            ArrayList<Integer> listCheck = new ArrayList<>();
            StringBuilder html = new StringBuilder(), valueBulder, hiraBulder;

            int first_kanji = -1;
            for (int i = 0; i < value.length(); i++) {
                if (isKanji(value.charAt(i))) {
                    first_kanji = i;
                    break;
                }
            }

            if (first_kanji == -1)
                return value;

            try {
                if (first_kanji > 0) {
                    String hira_first = value.substring(0, first_kanji);
                    html.append(hira_first);
                    valueBulder = new StringBuilder(value.substring(first_kanji));
                    hiraBulder = new StringBuilder(hira.replace(" ", "").substring(hira_first.replace(" ", "").length()));
                } else {
                    valueBulder = new StringBuilder(value);
                    hiraBulder = new StringBuilder(hira.replace(" ", ""));
                }

                boolean isKanji = true;

                for (int i = 0; i < valueBulder.length() - 2; i++) {
                    if (isKanji(valueBulder.charAt(i)) && valueBulder.charAt(i + 1) == ' ' &&
                            (isKanji(valueBulder.charAt(i + 2)) || valueBulder.charAt(i + 2) == ' '))
                        valueBulder = new StringBuilder(valueBulder.substring(0, i + 1) + valueBulder.substring(i + 2));
                }

                for (int i = 0; i < valueBulder.length(); i++) {
                    if (isKanji(valueBulder.charAt(i)) == isKanji) {
                        listCheck.add(i);
                        isKanji = !isKanji;
                    }
                }

                if (listCheck.size() == 1) {
                    html.append(String.format(tagRuby, valueBulder.toString(), hiraBulder.toString()));
                } else {

                    for (int i = 0; i < listCheck.size(); i += 2) {
                        if (i + 1 == listCheck.size() - 1) {
                            String hira_last = valueBulder.toString().substring(listCheck.get(i + 1));
                            String hira_bulder = hiraBulder.toString();
                            int pos = hira_bulder.indexOf(hira_last.replace(" ", ""));
                            String hira_string = "";
                            if (pos > 0)
                                hira_string = hira_bulder.substring(0, pos);
                            html.append(String.format(tagRuby, valueBulder.toString().substring(listCheck.get(i), listCheck.get(i + 1)), hira_string));
                            html.append(hira_last);
                        } else if (i == listCheck.size() - 1) {
                            html.append(String.format(tagRuby, valueBulder.toString().substring(listCheck.get(i)), hiraBulder.toString()));
                        } else {
                            String hira_mid = valueBulder.toString().substring(listCheck.get(i + 1), listCheck.get(i + 2));
                            String hira_bulder = hiraBulder.toString();
                            int pos = hira_bulder.indexOf(hira_mid.replace(" ", ""));

                            String hira_string = "";
                            if (pos > 0)
                                hira_string = hira_bulder.substring(0, pos);
                            else
                                pos = valueBulder.toString().substring(listCheck.get(i), listCheck.get(i + 1)).length();
                            html.append(String.format(tagRuby, valueBulder.toString().substring(listCheck.get(i), listCheck.get(i + 1)), hira_string));
                            html.append(hira_mid);

                            hiraBulder = new StringBuilder(hira_bulder.substring(pos + hira_mid.replace(" ", "").trim().length()));
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                return value;
            }
            return html.toString();
        }

        public static boolean isHira(char ch) {
            return HIRAGANA_START <= ch && ch <= HIRAGANA_END;
        }

        private static boolean isKanji(char ch) {
            return ch != '・' && ch != '、' && ch != '「' && ch != '」' &&
                    (KANJI_START <= ch && ch <= KANJI_END || RARE_KANJI_START <= ch && ch <= RARE_KANJI_END || JAPANESE_STYLE_START <= ch && ch <= JAPANESE_STYLE_END || KATAKANA_START <= ch && ch <= KATAKANA_END || KATAKANA_PHONETIC_START <= ch && ch <= KATAKANA_PHONETIC_END);
        }



        public static String ConvertDoubleToDate(Double number) {
            Date itemDate = new Date((long) (number * 1));
            return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(itemDate);
        }

        public static String parseDateToddMMyyyy(String time) {
            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            String outputPattern = "dd/MM/yyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.UK);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.UK);

            Date date;
            String str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return str;
        }

        /*
            get string from asset file
         */
        public static String getStringFromAsset(Context context, String path) throws IOException {
            StringBuilder buf = new StringBuilder();
            InputStream json = context.getAssets().open(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();

            return buf.toString();
        }

        public static String stringToHex(String str) {
            int unicode = 0;
            for (int i = 0; i < str.length(); i++) {
                unicode = unicode * 10 + (int) str.charAt(i);
            }
            return Integer.toHexString(unicode);
        }

        /*
            Example
         */
        public static String enableFurigana(String jap) {
            StringBuilder html = new StringBuilder();
            String[] _ref = jap.split("\t");
            for (String j : _ref) {
                String[] _ref1 = j.split(" ");
                String writting = "";
                String reading = "";
                if (_ref1.length <= 2) {
                    writting = _ref1[0];
                    html.append(writting);
                } else if (_ref1.length == 3) {
                    writting = _ref1[0];
                    reading = _ref1[2];
                    html.append("{").append(writting).append(";").append(reading).append("}");
                }
            }
            return html.toString();
        }

        public static String disableFurigana(String jap) {
            StringBuilder html = new StringBuilder();
            String[] _ref = jap.split("\t");
            for (String j : _ref) {
                String[] _ref1 = j.split(" ");
                String writting = "";
                writting = _ref1[0];
                html.append(writting);
            }
            return html.toString();
        }

        public static String loadList(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
