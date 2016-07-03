package com.onlineteammakers.bottlechat;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.onlineteammakers.bottlechat.Intent.UserInformation;
import com.onlineteammakers.bottlechat.TelegramProperties.BuildVars;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class IntroLoginActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "oSCN4HAVNMf9whsHyITpuzJAH";
    private static final String TWITTER_SECRET = "xAy3q6VJxQ4glWIEtzKomEzrsroJqILlsfsYxzaRTiQHNToxNk";

    private ViewPager viewPager;
    private ImageView topImage1;
    private ImageView topImage2;
    private ViewGroup listSlices;
    private int finalSlice = 0;
    private boolean newSlice = false;
    private boolean btnPressed = false;
    private int[] icons;
    private int[] titles;
    private int[] messages;
    private UserInformation user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_BottleChat);
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // check if an ipad is running this app
        if (false) {
            // Todo: Check if an ipad running this app!
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.intro_layout_content);
        }

        // Implementing list icons and message
        icons = new int[] {
                R.drawable.intro1,
                R.drawable.intro3,
                R.drawable.intro2,
                R.drawable.intro4,
                R.drawable.intro5,
                R.drawable.intro7,
                R.drawable.intro6
        };

        titles = new int[] {
                R.string.slice1_title,
                R.string.slice2_title,
                R.string.slice3_title,
                R.string.slice4_title,
                R.string.slice5_title,
                R.string.slice6_title,
                R.string.slice7_title
        };

        messages = new int[] {
                R.string.slice1_message,
                R.string.slice2_message,
                R.string.slice3_message,
                R.string.slice4_message,
                R.string.slice5_message,
                R.string.slice6_message,
                R.string.slice7_message
        };

        viewPager = (ViewPager) findViewById(R.id.intro_view_pager);
        // Implementing Login by other options
        Button otherLoginBtn = (Button) findViewById(R.id.login_options_button);
        otherLoginBtn.setText(R.string.login_by_options);
        otherLoginBtn.setBackgroundColor(getResources().getColor(R.color.intro_button));

        // Implementing buttons to login by phone number
        DigitsAuthButton phoneLoginBtn = (DigitsAuthButton) findViewById(R.id.phone_login_button);
        phoneLoginBtn.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();

                // Implementing UserInformation
                // -- Will lead to another page for more registration
                user.setUserPhoneNumber(phoneNumber);
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });

        phoneLoginBtn.setText(R.string.login_by_phone);
        phoneLoginBtn.setBackgroundColor(getResources().getColor(R.color.intro_button));

        topImage1 = (ImageView) findViewById(R.id.icon_image1);
        topImage2 = (ImageView) findViewById(R.id.icon_image2);
        listSlices = (ViewGroup) findViewById(R.id.slice_dots);
        topImage2.setVisibility(View.GONE);

        viewPager.setAdapter(new IntroAdapter());
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE || i == ViewPager.SCROLL_STATE_SETTLING) {
                    if (finalSlice != viewPager.getCurrentItem()) {
                        finalSlice = viewPager.getCurrentItem();

                        final ImageView fadeoutImage;
                        final ImageView fadeinImage;
                        if (topImage1.getVisibility() == View.VISIBLE) {
                            fadeoutImage = topImage1;
                            fadeinImage = topImage2;

                        } else {
                            fadeoutImage = topImage2;
                            fadeinImage = topImage1;
                        }

                        fadeinImage.bringToFront();
                        fadeinImage.setImageResource(icons[finalSlice]);
                        fadeinImage.clearAnimation();
                        fadeoutImage.clearAnimation();

                        Animation outAnimation = AnimationUtils.loadAnimation(IntroLoginActivity.this, R.anim.icon_faded_out);
                        outAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                fadeoutImage.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        Animation inAnimation = AnimationUtils.loadAnimation(IntroLoginActivity.this, R.anim.icon_faded_in);
                        inAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                fadeinImage.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                        fadeoutImage.startAnimation(outAnimation);
                        fadeinImage.startAnimation(inAnimation);
                    }
                }
            }
        });


        if (BuildVars.DEBUG_VERSION) {
            phoneLoginBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Todo: Added something here
                    return true;
                }
            });
        }

        newSlice = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (newSlice) {
            viewPager.setCurrentItem(0);
            finalSlice = 6;
            newSlice = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private class IntroAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.intro_layout_view, null);
            TextView headerTextView = (TextView) view.findViewById(R.id.header_text);
            TextView messageTextView = (TextView) view.findViewById(R.id.message_text);
            container.addView(view, 0);

            headerTextView.setText(getString(titles[position]));
            messageTextView.setText(Html.fromHtml(getString(messages[position])));
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            int count = listSlices.getChildCount();
            for (int a = 0; a < count; a++) {
                View child = listSlices.getChildAt(a);
                if (a == position) {
                    child.setBackgroundColor(0xff2ca5e0);
                } else {
                    child.setBackgroundColor(0xffbbbbbb);
                }
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            if (observer != null) {
                super.unregisterDataSetObserver(observer);
            }
        }
    }
}


