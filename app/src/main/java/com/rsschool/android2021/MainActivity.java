package com.rsschool.android2021;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Exchange{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment,"FIRST");
        // TODO: invoke function which apply changes of the transaction
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        // TODO: implement it
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, "SECOND");
        // TODO: invoke function which apply changes of the transaction
        transaction.commit();
    }

    @Override
    public void firstFragmentOpenEx(int result) {
        openFirstFragment(result);
    }

    @Override
    public void secondFragmentOpenEx(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackPressed() {
        Fragment f = getVisibleFragment();
        if(f.getTag().equals("SECOND")) {
            try {
                String fText = ((SecondFragment) f).getResult().getText().toString();
                int fValue = Integer.parseInt(fText);
                openFirstFragment(fValue);
            }
            catch (Exception e) {
                Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onBackPressed();
        }
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
}
