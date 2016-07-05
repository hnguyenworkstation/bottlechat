package com.onlineteammakers.bottlechat.Intent;

/**
 * Created by jason on 7/4/16.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class PostTextView extends TextView {

    public PostTextView(Context context) {
        super(context);
        init();
    }

    public PostTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PostTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Regular.ttf");
        setTypeface(tf);
    }
}
