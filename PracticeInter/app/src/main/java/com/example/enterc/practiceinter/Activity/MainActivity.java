package com.example.enterc.practiceinter.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.enterc.practiceinter.Model.Helper.NetworkChangeReceiver;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.ViewPagerAdapterMain;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    ViewPagerAdapterMain viewPagerAdapter;
    private NetworkChangeReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        receiver = new NetworkChangeReceiver();

         final IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
         registerReceiver(receiver, filter);


        viewPagerAdapter = new ViewPagerAdapterMain(getSupportFragmentManager());
        mViewPager = findViewById(R.id.vp_test);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.video: {
                        toolbar.setTitle("Video");
                        mViewPager.setCurrentItem(0);
                        return true;
                    }
                    case R.id.account: {
                        toolbar.setTitle("Account");
                        mViewPager.setCurrentItem(1);
                        return true;
                    }
                    case R.id.news: {
                        toolbar.setTitle("News Feed");
                        mViewPager.setCurrentItem(2);
                        return true;
                    }
                    case R.id.more: {
                        toolbar.setTitle("More");
                        mViewPager.setCurrentItem(3);
                        return true;
                    }
                }
                return false;
            }
        });

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef   = database.getReference("ListFolow");
//
//        myRef.orderByValue().limitToLast(4).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.d("AAA", "The " + dataSnapshot.getKey() + " dinosaur's score is " + dataSnapshot.getValue());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//              for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                  String key = snapshot.getKey();
//                  myRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
//                      @Override
//                      public void onDataChange(DataSnapshot dataSnapshot) {
//                          HashMap<String,Object> hashMap= (HashMap<String, Object>) dataSnapshot.getValue();
//                          Log.d("AAA",hashMap.get("name").toString());
//                      }
//
//                      @Override
//                      public void onCancelled(DatabaseError databaseError) {
//
//                      }
//                  });
//
//              }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        myRef.child("a").child("avatar").setValue("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnKha-31-UH7k38PL3tfSiJqPDjJyOSa6qte1BQCE-Iv7FiAf-");
//        myRef.child("a").child("id_singer").setValue("123");
//        myRef.child("a").child("name").setValue("Jack");
//        myRef.child("a").child("type").setValue("user_singer");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_search){
//            Intent intent = new Intent(this,PlayVideo.class);
//            intent.putExtra("URL","https://www.youtube.com/watch?v=VaxrgVwpgjc");
//            intent.putExtra("id","1569");
//            intent.putExtra("name","BIG DADDY");
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        receiver = new NetworkChangeReceiver();

        final IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}
