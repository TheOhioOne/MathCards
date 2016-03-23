package com.example.android.mathcards;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing the back of the card.
 */
public class CardBackFragment extends Fragment {
    private static final String PARAM_RESULT = "ParamResult";
    private int result;

    public static  CardBackFragment createInstance (int result){
        CardBackFragment instance = new CardBackFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_RESULT,result);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_back, container, false);
        result = getArguments().getInt(PARAM_RESULT);
        setBackCardValues(view);
        return view;
    }

    private void setBackCardValues(View view) {
        TextView resultTextView = (TextView) view.findViewById(R.id.result);
        if (resultTextView != null)
            resultTextView.setText(String.valueOf(result));
    }
}
