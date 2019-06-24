package com.example.easytravel.budget.intro;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro
{
    @Override
    public void init(Bundle savedInstanceState)
    {
        addSlide(new IntroSlide1());
        addSlide(new IntroSlide2());
        addSlide(new IntroSlide3());
        addSlide(new IntroSlide4());
        addSlide(new IntroSlide5());
        addSlide(new IntroSlide6());
        addSlide(new IntroSlide7());
        addSlide(new IntroSlide8());
        addSlide(new IntroSlide9());
        addSlide(new IntroSlide10());
        addSlide(new IntroSlide11());
    }

    @Override
    public void onSkipPressed(Fragment fragment) {
        finish();
    }

    @Override
    public void onDonePressed(Fragment fragment) {
        finish();
    }
}


