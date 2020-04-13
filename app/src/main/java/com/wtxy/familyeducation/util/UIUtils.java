package com.wtxy.familyeducation.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.view.Gravity;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wtxy.familyeducation.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by htyuan on 15-5-15.
 */
public class UIUtils {
    /**
     * 将屏幕尺寸转化成像素个数;
     *
     * @param context : 上下文;
     * @param dpi     : 屏幕尺寸;
     * @return: 像素数;
     */
    public static int dpi2px(Context context, int dpi) {
        return (int) (context.getResources().getDisplayMetrics().density * dpi + 0.5f);
    }

    public static int sp2px(Context context, int sp) {
        int px = (int) (context.getResources().getDisplayMetrics().scaledDensity
                * sp + 0.5f);
        return px;
    }

    /**
     * 将px值转换为dip或dp值
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue
                / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue
                / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 关闭输入法界面;
     *
     * @param activity
     */
    public static void dismissInputmethod(Activity activity) {
        if (activity.getCurrentFocus() != null
                && activity.getCurrentFocus().getWindowToken() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity
                            .getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 打开输入法界面;
     *
     * @param et
     */
    public static void showInputmethod(EditText et) {
        if (et == null) {
            return;
        }
        InputMethodManager inputManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et, 0);
    }

    /**
     * 打开输入法
     *
     * @param editText
     */
    public static void popupSoftWareOfEditText(final EditText editText) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =

                                       (InputMethodManager) editText.getContext().getSystemService(
                                               Context.INPUT_METHOD_SERVICE);

                               inputManager.showSoftInput(editText, 0);

                           }
                       },

                200);
    }

    /**
     * 在屏幕中间显示Toast
     *
     * @param context : 上下文
     * @param infoRes : 消息id
     */
    public static void showShortToastInCenter(Context context, int infoRes) {
        Toast toast = Toast.makeText(context, infoRes, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 在屏幕中间显示Toast
     *
     * @param context : 上下文
     * @param info    : 消息
     */
    public static void showShortToastInCenter(Context context, String info) {
        //"\\|\\|\\|"在这里面不用转义符？
        info = info.replace("|||","\n");
        Toast toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示进度对话框;
     *
     * @param titleResId : 对话框标题资源ID;
     * @param msgResId   : 对话框内容资源ID;
     * @param listener   : 对话框按钮行为处理监听器;
     */
    public static ProgressDialog showProgressDialog(Context context,
                                                    int titleResId, int msgResId, DialogInterface.OnCancelListener listener) {
        return showProgressDialog(context,
                context.getResources().getString(titleResId), context
                        .getResources().getString(msgResId), listener);
    }

    public static ProgressDialog showProgressDialog(Context context,
                                                    String title, String message, DialogInterface.OnCancelListener listener) {

        return ProgressDialog.show(context, title, message, true, true,
                listener);
    }

    /**
     * 关闭进度对话框;
     *
     * @param dialog : 要关闭的进度对话框对象;
     */
    public static void dismissProgressDialog(Dialog dialog) {
        dismissDialog(dialog);
    }

    /**
     * 显示加载中对话框
     *
     * @param context
     * @return
     */
    public static Dialog showDialog(Context context) {
        Dialog dialog = showDialog(context, null);
        dialog.setCancelable(false);
        return dialog;
    }

    public static Dialog showDialog(Context context, DialogInterface.OnCancelListener listener) {
        Dialog dialog = new Dialog(context, R.style.LodingDialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setOnCancelListener(listener);
        dialog.show();
        return dialog;
    }

    /**
     * 关闭进度对话框;
     *
     * @param dialog : 要关闭的进度对话框对象;
     */
    public static void dismissDialog(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {

        }
    }


    /**
     * Given either a Spannable String or a regular String and a token, apply
     * the given CharacterStyle to the span between the tokens, and also
     * remove tokens.
     * <p/>
     * For example, {@code setSpanBetweenTokens("Hello ##world##!", "##",
     * new ForegroundColorSpan(0xFFFF0000));} will return a CharSequence
     * {@code "Hello world!"} with {@code world} in red.
     *
     * @param text  The text, with the tokens, to adjust.
     * @param token The token string; there should be at least two instances
     *              of token in text.
     * @param cs    The style to apply to the CharSequence. WARNING: You cannot
     *              send the same two instances of this parameter, otherwise
     *              the second call will remove the original span.
     * @return A Spannable CharSequence with the new style applied.
     * @see //http://developer.android.com/reference/android/text/style/CharacterStyle.html
     */
    public static CharSequence setSpanBetweenTokens(CharSequence text,
                                                    String token, CharacterStyle... cs) {
        // Start and end refer to the points where the span will apply
        int tokenLen = token.length();
        int start = text.toString().indexOf(token) + tokenLen;
        int end = text.toString().indexOf(token, start);

        if (start > -1 && end > -1) {
            // Copy the spannable string to a mutable spannable string
            SpannableStringBuilder ssb = new SpannableStringBuilder(text);
            for (CharacterStyle c : cs)
                ssb.setSpan(c, start, end, 0);

            // Delete the tokens before and after the span
            ssb.delete(end, end + tokenLen);
            ssb.delete(start - tokenLen, start);

            text = ssb;
        }

        return text;
    }

    /**
     * 设置 textView drawable
     *
     * @param context
     * @param drawableId
     * @param targetView
     * @param ltrd       0,1,2,3
     */
    public static void setDrawableLTRD(Context context, int drawableId, TextView targetView, int ltrd) {
        Resources res = context.getResources();
        Drawable drawable = res.getDrawable(drawableId);
//调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (ltrd) {
            case 0:
                targetView.setCompoundDrawables(drawable, null, null, null); //设置左图标
                break;
            case 1:
                targetView.setCompoundDrawables(null, drawable, null, null); //设置左图标
                break;
            case 2:
                targetView.setCompoundDrawables(null, null, drawable, null); //设置左图标
                break;
            case 3:
                targetView.setCompoundDrawables(null, null, null, drawable); //设置左图标
                break;
        }
    }

    /**
     * 设置textView的最大值
     *
     * @param textView
     * @param maxLength
     */
    public static void setEditMaxLength(TextView textView, int maxLength) {
        if (textView != null) {
            InputFilter[] fArray = new InputFilter[1];
            fArray[0] = new InputFilter.LengthFilter(maxLength);
            textView.setFilters(fArray);
        }
    }

    public static void setEditTextMaxLength(EditText editText, int maxLength) {
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(fArray);
    }
}
