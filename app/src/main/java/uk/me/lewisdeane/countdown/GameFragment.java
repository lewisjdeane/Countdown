package uk.me.lewisdeane.countdown;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameFragment extends Fragment {

    private static MainActivity.GAME_TYPE mGameType;

    private static Button[] mButtons = new Button[6]; /** Stores the buttons in an array/ **/
    private static HashMap<Button, String> mButtonNumMap = new HashMap<Button, String>(); /** HashMap relating button to value. **/
    private static TextView mDisplay, mAimDisplay;

    private static View mRootView;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameType = MainActivity.GAME_TYPE.getGameType(getArguments().getString(MainActivity.PARAM_GAME_MODE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_game, container, false);

        init();
        generateNumbers();

        Toast.makeText(getActivity(), mGameType.toString(), Toast.LENGTH_SHORT).show();

        return mRootView;
    }

    private void init(){
        mButtons[0] = (Button) mRootView.findViewById(R.id.fragment_game_button_one);
        mButtons[1] = (Button) mRootView.findViewById(R.id.fragment_game_button_two);
        mButtons[2] = (Button) mRootView.findViewById(R.id.fragment_game_button_three);
        mButtons[3] = (Button) mRootView.findViewById(R.id.fragment_game_button_four);
        mButtons[4] = (Button) mRootView.findViewById(R.id.fragment_game_button_five);
        mButtons[5] = (Button) mRootView.findViewById(R.id.fragment_game_button_six);

        mDisplay = (TextView) mRootView.findViewById(R.id.fragment_game_display);
        mAimDisplay = (TextView) mRootView.findViewById(R.id.fragment_game_display_aim);

        for(Button button : mButtons) {
            button.setOnClickListener(buttonListener);
            mButtonNumMap.put(button, "");
        }
    }

    private void generateNumbers(){
        Random random = new Random();

        HashSet<Integer> numbers = new HashSet<Integer>();

        for(int i = 0; i < 6; i++){
            int num = random.nextInt(10) + 1;
            while(numbers.contains(num))
                num = random.nextInt(10) + 1;
            numbers.add(num);
            mButtonNumMap.put(mButtons[i], "" + num);
            mButtons[i].setText(mButtonNumMap.get(mButtons[i]));
        }

        numbers.clear();
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            mDisplay.append(button.getText().toString());
            button.setText("-");
            mButtonNumMap.put(button, "-");
        }
    };
}
