package com.peterhuang.ttrscorer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS;

public class MainActivity extends ActionBarActivity {

    private boolean isNotEditable = false;
    private int selected = -1;
    private final int[] scores = new int[] {0, 0, 0, 0, 0};
    private final int[] views = new int[] {R.id.adjust1, R.id.adjust2, R.id.adjust3, R.id.adjust4, R.id.adjust5};
    private final int[] scoreViews = new int[] {R.id.score1, R.id.score2, R.id.score3, R.id.score4, R.id.score5};


    public int getSelected() {
        return selected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.peterhuang.ttrscorer.R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //ButtonPath VAN_CAL = new ButtonPath(new Button[] {(Button) findViewById(R.id.VAN_CAL_1), (Button) findViewById(R.id.VAN_CAL_2), (Button) findViewById(R.id.VAN_CAL_3)}, this);
        final ButtonPath VAN_CAL_1 = new ButtonPath(new int[] {R.id.VAN_CAL_1, R.id.VAN_CAL_2, R.id.VAN_CAL_3}, this);
        final ButtonPath SEA_CAL_1 = new ButtonPath(new int[] {R.id.SEA_CAL_1, R.id.SEA_CAL_2, R.id.SEA_CAL_3, R.id.SEA_CAL_4}, this);
        final ButtonPath VAN_SEA_1 = new ButtonPath(new int[] {R.id.VAN_SEA_1}, this);
        final ButtonPath VAN_SEA_2 = new ButtonPath(new int[] {R.id.VAN_SEA_1_2}, this);
        final ButtonPath SEA_POR_1 = new ButtonPath(new int[] {R.id.SEA_POR_1}, this);
        final ButtonPath SEA_POR_2 = new ButtonPath(new int[] {R.id.SEA_POR_1_2}, this);
        final ButtonPath POR_SFO_1 = new ButtonPath(new int[] {R.id.POR_SFO_1, R.id.POR_SFO_2, R.id.POR_SFO_3, R.id.POR_SFO_4, R.id.POR_SFO_5}, this);
        final ButtonPath POR_SFO_2 = new ButtonPath(new int[] {R.id.POR_SFO_1_2, R.id.POR_SFO_2_2, R.id.POR_SFO_3_2, R.id.POR_SFO_4_2, R.id.POR_SFO_5_2}, this);
        final ButtonPath SEA_HEL_1 = new ButtonPath(new int[] {R.id.SEA_HEL_1, R.id.SEA_HEL_2, R.id.SEA_HEL_3, R.id.SEA_HEL_4, R.id.SEA_HEL_5, R.id.SEA_HEL_6}, this);
        final ButtonPath POR_SLC_1 = new ButtonPath(new int[] {R.id.POR_SLC_1, R.id.POR_SLC_2, R.id.POR_SLC_3, R.id.POR_SLC_4, R.id.POR_SLC_5, R.id.POR_SLC_6}, this);
        final ButtonPath SFO_SLC_1 = new ButtonPath(new int[] {R.id.SFO_SLC_1, R.id.SFO_SLC_2, R.id.SFO_SLC_3, R.id.SFO_SLC_4, R.id.SFO_SLC_5}, this);
        final ButtonPath SFO_SLC_2 = new ButtonPath(new int[] {R.id.SFO_SLC_1_2, R.id.SFO_SLC_2_2, R.id.SFO_SLC_3_2, R.id.SFO_SLC_4_2, R.id.SFO_SLC_5_2}, this);
        final ButtonPath SFO_LAX_1 = new ButtonPath(new int[] {R.id.SFO_LAX_1, R.id.SFO_LAX_2, R.id.SFO_LAX_3}, this);
        final ButtonPath SFO_LAX_2 = new ButtonPath(new int[] {R.id.SFO_LAX_1_2, R.id.SFO_LAX_2_2, R.id.SFO_LAX_3_2}, this);
        final ButtonPath LAX_LAS_1 = new ButtonPath(new int[] {R.id.LAX_LAS_1, R.id.LAX_LAS_2}, this);
        final ButtonPath LAS_SLC_1 = new ButtonPath(new int[] {R.id.LAS_SLC_1, R.id.LAS_SLC_2, R.id.LAS_SLC_3}, this);
        final ButtonPath LAX_PHX_1 = new ButtonPath(new int[] {R.id.LAX_PHX_1, R.id.LAX_PHX_2, R.id.LAX_PHX_3}, this);
        final ButtonPath LAX_ELP_1 = new ButtonPath(new int[] {R.id.LAX_ELP_1, R.id.LAX_ELP_2, R.id.LAX_ELP_3, R.id.LAX_ELP_4, R.id.LAX_ELP_5, R.id.LAX_ELP_6}, this);
        final ButtonPath CAL_WIN_1 = new ButtonPath(new int[] {R.id.CAL_WIN_1, R.id.CAL_WIN_2, R.id.CAL_WIN_3, R.id.CAL_WIN_4, R.id.CAL_WIN_5, R.id.CAL_WIN_6}, this);
        final ButtonPath CAL_HEL_1 = new ButtonPath(new int[] {R.id.CAL_HEL_1, R.id.CAL_HEL_2, R.id.CAL_HEL_3, R.id.CAL_HEL_4}, this);
        final ButtonPath SLC_HEL_1 = new ButtonPath(new int[] {R.id.SLC_HEL_1, R.id.SLC_HEL_2, R.id.SLC_HEL_3}, this);
        final ButtonPath SLC_DEN_1 = new ButtonPath(new int[] {R.id.SLC_DEN_1, R.id.SLC_DEN_2, R.id.SLC_DEN_3}, this);
        final ButtonPath SLC_DEN_2 = new ButtonPath(new int[] {R.id.SLC_DEN_1_2, R.id.SLC_DEN_2_2, R.id.SLC_DEN_3_2}, this);
        final ButtonPath LAS_DEN_1 = new ButtonPath(new int[] {R.id.PHX_DEN_1, R.id.PHX_DEN_2, R.id.PHX_DEN_3, R.id.PHX_DEN_4, R.id.PHX_DEN_5}, this);
        final ButtonPath PHX_SFE_1 = new ButtonPath(new int[] {R.id.PHX_SFE_1, R.id.PHX_SFE_2, R.id.PHX_SFE_3}, this);
        final ButtonPath PHX_ELP_1 = new ButtonPath(new int[] {R.id.PHX_ELP_1, R.id.PHX_ELP_2, R.id.PHX_ELP_3}, this);
        final ButtonPath HEL_WIN_1 = new ButtonPath(new int[] {R.id.HEL_WIN_1, R.id.HEL_WIN_2, R.id.HEL_WIN_3, R.id.HEL_WIN_4}, this);
        final ButtonPath HEL_DEN_1 = new ButtonPath(new int[] {R.id.HEL_DEN_1, R.id.HEL_DEN_2, R.id.HEL_DEN_3, R.id.HEL_DEN_4}, this);
        final ButtonPath SFE_DEN_1 = new ButtonPath(new int[] {R.id.SFE_DEN_1, R.id.SFE_DEN_2}, this);
        final ButtonPath ELP_SFE_1 = new ButtonPath(new int[] {R.id.ELP_SFE_1, R.id.ELP_SFE_2}, this);
        final ButtonPath WIN_SSM_1 = new ButtonPath(new int[] {R.id.WIN_SSM_1, R.id.WIN_SSM_2, R.id.WIN_SSM_3, R.id.WIN_SSM_4, R.id.WIN_SSM_5, R.id.WIN_SSM_6}, this);
        final ButtonPath WIN_DUL_1 = new ButtonPath(new int[] {R.id.WIN_DUL_1, R.id.WIN_DUL_2, R.id.WIN_DUL_3, R.id.WIN_DUL_4}, this);
        final ButtonPath HEL_DUL_1 = new ButtonPath(new int[] {R.id.HEL_DUL_1, R.id.HEL_DUL_2, R.id.HEL_DUL_3, R.id.HEL_DUL_4, R.id.HEL_DUL_5, R.id.HEL_DUL_6}, this);
        final ButtonPath HEL_OMA_1 = new ButtonPath(new int[] {R.id.HEL_OMA_1, R.id.HEL_OMA_2, R.id.HEL_OMA_3, R.id.HEL_OMA_4, R.id.HEL_OMA_5}, this);
        final ButtonPath DEN_OMA_1 = new ButtonPath(new int[] {R.id.DEN_OMA_1, R.id.DEN_OMA_2, R.id.DEN_OMA_3, R.id.DEN_OMA_4}, this);
        final ButtonPath DEN_KAC_1 = new ButtonPath(new int[] {R.id.DEN_KAC_1, R.id.DEN_KAC_2, R.id.DEN_KAC_3, R.id.DEN_KAC_4}, this);
        final ButtonPath DEN_KAC_2 = new ButtonPath(new int[] {R.id.DEN_KAC_1_2, R.id.DEN_KAC_2_2, R.id.DEN_KAC_3_2, R.id.DEN_KAC_4_2}, this);
        final ButtonPath DEN_OKC_1 = new ButtonPath(new int[] {R.id.DEN_OKC_1, R.id.DEN_OKC_2, R.id.DEN_OKC_3, R.id.DEN_OKC_4}, this);
        final ButtonPath SFE_OKC_1 = new ButtonPath(new int[] {R.id.SFE_OKC_1, R.id.SFE_OKC_2, R.id.SFE_OKC_3}, this);
        final ButtonPath ELP_OKC_1 = new ButtonPath(new int[] {R.id.ELP_OKC_1, R.id.ELP_OKC_2, R.id.ELP_OKC_3, R.id.ELP_OKC_4, R.id.ELP_OKC_5}, this);
        final ButtonPath ELP_DAL_1 = new ButtonPath(new int[] {R.id.ELP_DAL_1, R.id.ELP_DAL_2, R.id.ELP_DAL_3, R.id.ELP_DAL_4}, this);
        final ButtonPath ELP_HOU_1 = new ButtonPath(new int[] {R.id.ELP_HOU_1, R.id.ELP_HOU_2, R.id.ELP_HOU_3, R.id.ELP_HOU_4, R.id.ELP_HOU_5, R.id.ELP_HOU_6}, this);
        final ButtonPath DUL_SSM_1 = new ButtonPath(new int[] {R.id.DUL_SSM_1, R.id.DUL_SSM_2, R.id.DUL_SSM_3}, this);
        final ButtonPath DUL_TOR_1 = new ButtonPath(new int[] {R.id.DUL_TOR_1, R.id.DUL_TOR_2, R.id.DUL_TOR_3, R.id.DUL_TOR_4, R.id.DUL_TOR_5, R.id.DUL_TOR_6}, this);
        final ButtonPath OMA_DUL_1 = new ButtonPath(new int[] {R.id.OMA_DUL_1, R.id.OMA_DUL_2}, this);
        final ButtonPath OMA_DUL_2 = new ButtonPath(new int[] {R.id.OMA_DUL_1_2, R.id.OMA_DUL_2_2}, this);
        final ButtonPath OMA_KAC_1 = new ButtonPath(new int[] {R.id.OMA_KAC_1}, this);
        final ButtonPath OMA_KAC_2 = new ButtonPath(new int[] {R.id.OMA_KAC_1_2}, this);
        final ButtonPath OKC_KAC_1 = new ButtonPath(new int[] {R.id.OKC_KAC_1, R.id.OKC_KAC_2}, this);
        final ButtonPath OKC_KAC_2 = new ButtonPath(new int[] {R.id.OKC_KAC_1_2, R.id.OKC_KAC_2_2}, this);
        final ButtonPath OKC_DAL_1 = new ButtonPath(new int[] {R.id.OKC_DAL_1, R.id.OKC_DAL_2}, this);
        final ButtonPath OKC_DAL_2 = new ButtonPath(new int[] {R.id.OKC_DAL_1_2, R.id.OKC_DAL_2_2}, this);
        final ButtonPath DAL_HOU_1 = new ButtonPath(new int[] {R.id.DAL_HOU_1}, this);
        final ButtonPath DAL_HOU_2 = new ButtonPath(new int[] {R.id.DAL_HOU_1_2}, this);
        final ButtonPath DUL_CHI_1 = new ButtonPath(new int[] {R.id.DUL_CHI_1, R.id.DUL_CHI_2, R.id.DUL_CHI_3}, this);
        final ButtonPath OMA_CHI_1 = new ButtonPath(new int[] {R.id.OMA_CHI_1, R.id.OMA_CHI_2, R.id.OMA_CHI_3, R.id.OMA_CHI_4}, this);
        final ButtonPath KAC_STL_1 = new ButtonPath(new int[] {R.id.KAC_STL_1, R.id.KAC_STL_2}, this);
        final ButtonPath KAC_STL_2 = new ButtonPath(new int[] {R.id.KAC_STL_1_2, R.id.KAC_STL_2_2}, this);
        final ButtonPath OKC_LTR_1 = new ButtonPath(new int[] {R.id.OKC_LTR_1, R.id.OKC_LTR_2}, this);
        final ButtonPath DAL_LTR_1 = new ButtonPath(new int[] {R.id.DAL_LTR_1, R.id.DAL_LTR_2}, this);
        final ButtonPath LTR_STL_1 = new ButtonPath(new int[] {R.id.LTR_STL_1, R.id.LTR_STL_2}, this);
        final ButtonPath STL_CHI_1 = new ButtonPath(new int[] {R.id.STL_CHI_1, R.id.STL_CHI_2}, this);
        final ButtonPath STL_CHI_2 = new ButtonPath(new int[] {R.id.STL_CHI_1_2, R.id.STL_CHI_2_2}, this);
        final ButtonPath CHI_TOR_1 = new ButtonPath(new int[] {R.id.CHI_TOR_1, R.id.CHI_TOR_2, R.id.CHI_TOR_3, R.id.CHI_TOR_4}, this);
        final ButtonPath SSM_MON_1 = new ButtonPath(new int[] {R.id.SSM_MON_1, R.id.SSM_MON_2, R.id.SSM_MON_3, R.id.SSM_MON_4, R.id.SSM_MON_5}, this);
        final ButtonPath SSM_TOR_1 = new ButtonPath(new int[] {R.id.SSM_TOR_1, R.id.SSM_TOR_2}, this);
        final ButtonPath HOU_NOR_1 = new ButtonPath(new int[] {R.id.HOU_NOR_1, R.id.HOU_NOR_2}, this);
        final ButtonPath LTR_NOR_1 = new ButtonPath(new int[] {R.id.LTR_NOR_1, R.id.LTR_NOR_2, R.id.LTR_NOR_3}, this);
        final ButtonPath LTR_NAS_1 = new ButtonPath(new int[] {R.id.LTR_NAS_1, R.id.LTR_NAS_2, R.id.LTR_NAS_3}, this);
        final ButtonPath STL_NAS_1 = new ButtonPath(new int[] {R.id.STL_NAS_1, R.id.STL_NAS_2}, this);
        final ButtonPath STL_PIT_1 = new ButtonPath(new int[] {R.id.STL_PIT_1, R.id.STL_PIT_2, R.id.STL_PIT_3, R.id.STL_PIT_4, R.id.STL_PIT_5}, this);
        final ButtonPath CHI_PIT_1 = new ButtonPath(new int[] {R.id.CHI_PIT_1, R.id.CHI_PIT_2, R.id.CHI_PIT_3}, this);
        final ButtonPath CHI_PIT_2 = new ButtonPath(new int[] {R.id.CHI_PIT_1_2, R.id.CHI_PIT_2_2, R.id.CHI_PIT_3_2}, this);
        final ButtonPath NOR_ATL_1 = new ButtonPath(new int[] {R.id.NOR_ATL_1, R.id.NOR_ATL_2, R.id.NOR_ATL_3, R.id.NOR_ATL_4}, this);
        final ButtonPath NOR_ATL_2 = new ButtonPath(new int[] {R.id.NOR_ATL_1_2, R.id.NOR_ATL_2_2, R.id.NOR_ATL_3_2, R.id.NOR_ATL_4_2}, this);
        final ButtonPath NAS_ATL_1 = new ButtonPath(new int[] {R.id.NAS_ATL_1}, this);
        final ButtonPath NAS_PIT_1 = new ButtonPath(new int[] {R.id.NAS_PIT_1, R.id.NAS_PIT_2, R.id.NAS_PIT_3, R.id.NAS_PIT_4}, this);
        final ButtonPath TOR_MON_1 = new ButtonPath(new int[] {R.id.TOR_MON_1, R.id.TOR_MON_2, R.id.TOR_MON_3}, this);
        final ButtonPath MON_NYC_1 = new ButtonPath(new int[] {R.id.MON_NYC_1, R.id.MON_NYC_2, R.id.MON_NYC_3}, this);
        final ButtonPath MON_BOS_1 = new ButtonPath(new int[] {R.id.MON_BOS_1, R.id.MON_BOS_2}, this);
        final ButtonPath MON_BOS_2 = new ButtonPath(new int[] {R.id.MON_BOS_1_2, R.id.MON_BOS_2_2}, this);
        final ButtonPath NYC_BOS_1 = new ButtonPath(new int[] {R.id.NYC_BOS_1, R.id.NYC_BOS_2}, this);
        final ButtonPath NYC_BOS_2 = new ButtonPath(new int[] {R.id.NYC_BOS_1_2, R.id.NYC_BOS_2_2}, this);
        final ButtonPath PIT_NYC_1 = new ButtonPath(new int[] {R.id.PIT_NYC_1, R.id.PIT_NYC_2}, this);
        final ButtonPath PIT_NYC_2 = new ButtonPath(new int[] {R.id.PIT_NYC_1_2, R.id.PIT_NYC_2_2}, this);
        final ButtonPath TOR_PIT_1 = new ButtonPath(new int[] {R.id.TOR_PIT_1, R.id.TOR_PIT_2}, this);
        final ButtonPath NYC_WAS_1 = new ButtonPath(new int[] {R.id.NYC_WAS_1, R.id.NYC_WAS_2}, this);
        final ButtonPath NYC_WAS_2 = new ButtonPath(new int[] {R.id.NYC_WAS_1_2, R.id.NYC_WAS_2_2}, this);
        final ButtonPath PIT_WAS_1 = new ButtonPath(new int[] {R.id.PIT_WAS_1, R.id.PIT_WAS_2}, this);
        final ButtonPath PIT_RAL_1 = new ButtonPath(new int[] {R.id.PIT_RAL_1, R.id.PIT_RAL_2}, this);
        final ButtonPath RAL_WAS_1 = new ButtonPath(new int[] {R.id.RAL_WAS_1, R.id.RAL_WAS_2}, this);
        final ButtonPath RAL_WAS_2 = new ButtonPath(new int[] {R.id.RAL_WAS_1_2, R.id.RAL_WAS_2_2}, this);
        final ButtonPath NAS_RAL_1 = new ButtonPath(new int[] {R.id.NAS_RAL_1, R.id.NAS_RAL_2, R.id.NAS_RAL_3}, this);
        final ButtonPath ATL_RAL_1 = new ButtonPath(new int[] {R.id.ATL_RAL_1, R.id.ATL_RAL_2}, this);
        final ButtonPath ATL_RAL_2 = new ButtonPath(new int[] {R.id.ATL_RAL_1_2, R.id.ATL_RAL_2_2}, this);
        final ButtonPath RAL_CHA_1 = new ButtonPath(new int[] {R.id.RAL_CHA_1, R.id.RAL_CHA_2}, this);
        final ButtonPath ATL_CHA_1 = new ButtonPath(new int[] {R.id.ATL_CHA_1, R.id.ATL_CHA_2}, this);
        final ButtonPath NOR_MIA_1 = new ButtonPath(new int[] {R.id.NOR_MIA_1, R.id.NOR_MIA_2, R.id.NOR_MIA_3, R.id.NOR_MIA_4, R.id.NOR_MIA_5, R.id.NOR_MIA_6}, this);
        final ButtonPath ATL_MIA_1 = new ButtonPath(new int[] {R.id.ATL_MIA_1, R.id.ATL_MIA_2, R.id.ATL_MIA_3, R.id.ATL_MIA_4, R.id.ATL_MIA_5}, this);
        final ButtonPath CHA_MIA_1 = new ButtonPath(new int[] {R.id.CHA_MIA_1, R.id.CHA_MIA_2, R.id.CHA_MIA_3, R.id.CHA_MIA_4}, this);

        ImageView image1 = (ImageView) findViewById(R.id.imageView);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), com.peterhuang.ttrscorer.R.drawable.ttr, opts);
        int dp = 333;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float pixelWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
//        Log.i("MainActivity", "pW: " + pixelWidth);
        image1.setImageBitmap(Bitmap.createScaledBitmap(bm, (int) pixelWidth * 2, (int) (pixelWidth * .64 * 2 ), false));
        bm.recycle();

        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                switch (v.getId()) {
                    case R.id.adjust1:
                    case R.id.adjust2:
                    case R.id.adjust3:
                    case R.id.adjust4:
                    case R.id.adjust5:
//                        Log.i("MainActivity", "focusChange: " + v.toString());
                        isNotEditable = true;
                        String oldValue = ((EditText) v).getText().toString();
                        if (hasFocus) {
                            if (!oldValue.isEmpty()) {
//                                Log.i("MainActivity", "oldValue: " + oldValue);
                                for (int i = 0; i < views.length; i++) {
                                    if (views[i] == v.getId()) {
                                        int value = Integer.valueOf(oldValue);
                                        changeScore(i, -value);
                                        break;
                                    }
                                }
                                ((EditText) v).getText().clear();
                            }
                        } else if (oldValue.matches("^0+$")) {
                            ((EditText) v).getText().clear();
                        }
                        isNotEditable = false;
                        break;
                }
            }
        };

        findViewById(R.id.adjust1).setOnFocusChangeListener(onFocusChangeListener);
        findViewById(R.id.adjust2).setOnFocusChangeListener(onFocusChangeListener);
        findViewById(R.id.adjust3).setOnFocusChangeListener(onFocusChangeListener);
        findViewById(R.id.adjust4).setOnFocusChangeListener(onFocusChangeListener);
        findViewById(R.id.adjust5).setOnFocusChangeListener(onFocusChangeListener);

        findViewById(R.id.focus).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (getCurrentFocus() != null) {
//                        Log.i("MainActivity", "focusTouch called");
                        if (selected > -1) {
                            findViewById(scoreViews[selected]).setBackgroundResource(0);
                        }
                        selected = -1;
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), HIDE_NOT_ALWAYS);
                        getCurrentFocus().clearFocus();
                    }
                }
                return true;
            }
        });

        ((EditText) findViewById(R.id.adjust5)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    Log.i("MainActivity", "done called");
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), HIDE_NOT_ALWAYS);
                    getCurrentFocus().clearFocus();
                }
                return false;
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isNotEditable) {
                    return;
                }
//                Log.i("MainActivity", "beforeTextChanged called " + s);
                int value = (s.toString().isEmpty() || s.toString().equals("-")) ? 0 : Integer.valueOf(s.toString());
                for (int i = 0; i < views.length; i++) {
                    EditText editText = (EditText) findViewById(views[i]);
                    if (editText.getText().hashCode() == s.hashCode()) {
                        changeScore(i, -value);
                        break;
                    }
                }
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void afterTextChanged(Editable s) {
                if (isNotEditable) {
                    return;
                }
//                Log.i("MainActivity", "onTextChanged called");
                int value = (s.toString().isEmpty() || s.toString().equals("-")) ? 0 : Integer.valueOf(s.toString());
                for (int i = 0; i < views.length; i++) {
                    EditText editText = (EditText) findViewById(views[i]);
                    if (editText.getText().hashCode() == s.hashCode()) {
                        changeScore(i, value);
                        if (!editText.getText().toString().isEmpty() && !editText.getText().toString().equals("-") && !editText.getText().toString().equals(String.valueOf(value)) ) {
                            isNotEditable = true;
                            editText.getText().clear();
                            if (value != 0) {
                                editText.getText().append(String.valueOf(scores[i]));
                            }
                            isNotEditable = false;
                        }
                        break;
                    }
                }
            }
        };

        for (int view : views) {
            ((TextView) findViewById(view)).addTextChangedListener(textWatcher);
        }

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    Log.i("MainActivity", "onTouchListener called");
                    if (selected > -1) {
                        findViewById(scoreViews[selected]).setBackgroundResource(0);
                    }
                    selected = -1;
                    for (int i = 0; i < scoreViews.length; i++) {
                        if (scoreViews[i] == v.getId()) {
                            selected = i;
                            findViewById(scoreViews[i]).setBackgroundResource(R.drawable.back);
                            break;
                        }
                    }
                    if (getCurrentFocus() != null) {
//                        Log.i("MainActivity", "focus cleared");
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), HIDE_NOT_ALWAYS);
                        getCurrentFocus().clearFocus();
                    }
                }
                return true;
            }
        };

        for (int scoreView : scoreViews) {
            findViewById(scoreView).setOnTouchListener(onTouchListener);
        }

        findViewById(R.id.clear).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (selected > -1) {
                    findViewById(scoreViews[selected]).setBackgroundResource(0);
                }
                selected = -1;
                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), HIDE_NOT_ALWAYS);
                getCurrentFocus().clearFocus();
                isNotEditable = true;
                for (int i = 0; i < views.length; i++) {
                    String oldValue = ((EditText) findViewById(views[i])).getText().toString();
                    if (!oldValue.isEmpty()) {
                        int value = Integer.valueOf(oldValue);
                        changeScore(i, -value);
                        ((EditText) findViewById(views[i])).getText().clear();
                    }
                }
                isNotEditable = false;
                VAN_CAL_1.updateState(-1);
                SEA_CAL_1.updateState(-1);
                VAN_SEA_1.updateState(-1);
                VAN_SEA_2.updateState(-1);
                SEA_POR_1.updateState(-1);
                SEA_POR_2.updateState(-1);
                POR_SFO_1.updateState(-1);
                POR_SFO_2.updateState(-1);
                SEA_HEL_1.updateState(-1);
                POR_SLC_1.updateState(-1);
                SFO_SLC_1.updateState(-1);
                SFO_SLC_2.updateState(-1);
                SFO_LAX_1.updateState(-1);
                SFO_LAX_2.updateState(-1);
                LAX_LAS_1.updateState(-1);
                LAS_SLC_1.updateState(-1);
                LAX_PHX_1.updateState(-1);
                LAX_ELP_1.updateState(-1);
                CAL_WIN_1.updateState(-1);
                CAL_HEL_1.updateState(-1);
                SLC_HEL_1.updateState(-1);
                SLC_DEN_1.updateState(-1);
                SLC_DEN_2.updateState(-1);
                LAS_DEN_1.updateState(-1);
                PHX_SFE_1.updateState(-1);
                PHX_ELP_1.updateState(-1);
                HEL_WIN_1.updateState(-1);
                HEL_DEN_1.updateState(-1);
                SFE_DEN_1.updateState(-1);
                ELP_SFE_1.updateState(-1);
                WIN_SSM_1.updateState(-1);
                WIN_DUL_1.updateState(-1);
                HEL_DUL_1.updateState(-1);
                HEL_OMA_1.updateState(-1);
                DEN_OMA_1.updateState(-1);
                DEN_KAC_1.updateState(-1);
                DEN_KAC_2.updateState(-1);
                DEN_OKC_1.updateState(-1);
                SFE_OKC_1.updateState(-1);
                ELP_OKC_1.updateState(-1);
                ELP_DAL_1.updateState(-1);
                ELP_HOU_1.updateState(-1);
                DUL_SSM_1.updateState(-1);
                DUL_TOR_1.updateState(-1);
                OMA_DUL_1.updateState(-1);
                OMA_DUL_2.updateState(-1);
                OMA_KAC_1.updateState(-1);
                OMA_KAC_2.updateState(-1);
                OKC_KAC_1.updateState(-1);
                OKC_KAC_2.updateState(-1);
                OKC_DAL_1.updateState(-1);
                OKC_DAL_2.updateState(-1);
                DAL_HOU_1.updateState(-1);
                DAL_HOU_2.updateState(-1);
                DUL_CHI_1.updateState(-1);
                OMA_CHI_1.updateState(-1);
                KAC_STL_1.updateState(-1);
                KAC_STL_2.updateState(-1);
                OKC_LTR_1.updateState(-1);
                DAL_LTR_1.updateState(-1);
                LTR_STL_1.updateState(-1);
                STL_CHI_1.updateState(-1);
                STL_CHI_2.updateState(-1);
                CHI_TOR_1.updateState(-1);
                SSM_MON_1.updateState(-1);
                SSM_TOR_1.updateState(-1);
                HOU_NOR_1.updateState(-1);
                LTR_NOR_1.updateState(-1);
                LTR_NAS_1.updateState(-1);
                STL_NAS_1.updateState(-1);
                STL_PIT_1.updateState(-1);
                CHI_PIT_1.updateState(-1);
                CHI_PIT_2.updateState(-1);
                NOR_ATL_1.updateState(-1);
                NOR_ATL_2.updateState(-1);
                NAS_ATL_1.updateState(-1);
                NAS_PIT_1.updateState(-1);
                TOR_MON_1.updateState(-1);
                MON_NYC_1.updateState(-1);
                MON_BOS_1.updateState(-1);
                MON_BOS_2.updateState(-1);
                NYC_BOS_1.updateState(-1);
                NYC_BOS_2.updateState(-1);
                PIT_NYC_1.updateState(-1);
                PIT_NYC_2.updateState(-1);
                TOR_PIT_1.updateState(-1);
                NYC_WAS_1.updateState(-1);
                NYC_WAS_2.updateState(-1);
                PIT_WAS_1.updateState(-1);
                PIT_RAL_1.updateState(-1);
                RAL_WAS_1.updateState(-1);
                RAL_WAS_2.updateState(-1);
                NAS_RAL_1.updateState(-1);
                ATL_RAL_1.updateState(-1);
                ATL_RAL_2.updateState(-1);
                RAL_CHA_1.updateState(-1);
                ATL_CHA_1.updateState(-1);
                NOR_MIA_1.updateState(-1);
                ATL_MIA_1.updateState(-1);
                CHA_MIA_1.updateState(-1);
                return true;
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), HIDE_NOT_ALWAYS);
        findViewById(R.id.focus).requestFocus();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(com.peterhuang.ttrscorer.R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == com.peterhuang.ttrscorer.R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void changeScore(int index, int value) {
        scores[index] += value;
        ((TextView) findViewById(scoreViews[index])).setText(String.valueOf(scores[index]));
    }

}
