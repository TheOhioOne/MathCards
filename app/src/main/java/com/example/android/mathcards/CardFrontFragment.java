package com.example.android.mathcards;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing the front of the card.
 */
public class CardFrontFragment extends Fragment {
    private static final String PARAM_TOP_NUMBER = "ParamTopNumber";
    private static final String PARAM_BOTTOM_NUMBER = "ParamBottomNumber";
    private static final String PARAM_OPERATOR = "ParamOperator";
    private MathPresenter presenter = new MathPresenter();
    private TextView topNumberTextView;
    private TextView bottomNumberTextView;
    private TextView operatorTextView;

    public static CardFrontFragment createInstance (int topNumber, int bottomNumber, String operator){
        CardFrontFragment instance = new CardFrontFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_TOP_NUMBER, topNumber);
        args.putInt(PARAM_BOTTOM_NUMBER, bottomNumber);
        args.putString(PARAM_OPERATOR, operator);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_front, container, false);
        topNumberTextView = (TextView) view.findViewById(R.id.top_number_question);
        bottomNumberTextView = (TextView) view.findViewById(R.id.bottom_number_question);
        operatorTextView = (TextView) view.findViewById(R.id.operator);
        int topNumber = getArguments().getInt(PARAM_TOP_NUMBER);
        int bottomNumber = getArguments().getInt(PARAM_BOTTOM_NUMBER);
        String operator = getArguments().getString(PARAM_OPERATOR);
        setFrontCardValues(topNumber, bottomNumber, operator);
        return view;
    }

    public void setFrontCardValues(int topNumber,int bottomNumber, String operator){

        if (topNumberTextView != null) {
            if ("2".equals(operator.trim())) {
                topNumberTextView.setVisibility(View.GONE);
            } else {
                topNumberTextView.setVisibility(View.VISIBLE);
                topNumberTextView.setText(String.valueOf(topNumber));
            }
        }
        if (bottomNumberTextView != null) {
            bottomNumberTextView.setText(String.valueOf(bottomNumber));
        }
        if (operatorTextView != null) {
            operatorTextView.setText(operator);
        }
    }

}
