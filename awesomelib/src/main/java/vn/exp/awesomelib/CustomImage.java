package vn.exp.awesomelib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomImage extends ImageView {
    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();
    private float mBorder;


    public CustomImage(Context context) {
        this(context, null);
    }

    public CustomImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomImage, 0, 0);
            try {
                int number = ta.getInteger(R.styleable.CustomImage_shape, 0);
                mBorder = ta.getDimension(R.styleable.CustomImage_border_size, 0);
                int borderColor = ta.getColor(R.styleable.CustomImage_border_color, 0);
                int fillColor = ta.getColor(R.styleable.CustomImage_fill_color, 0);
                setData(number, mBorder, borderColor, fillColor);
            } finally {
                ta.recycle();
            }
        }
    }

    private void setData(int number, float borderSize, int borderColor, int fillColor) {
        mPaint.setColor(borderColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderSize);
//        mPaint.setColor(fillColor);
//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);

        invalidate();
    }

    /*
     * Set the canvas bounds here
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);

        mRectF.set(0, 0, screenWidth, screenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mRectF.centerX(), mRectF.centerY(), Math.min(mRectF.width(), mRectF.height()) / 2 - mBorder, mPaint);
    }
}
