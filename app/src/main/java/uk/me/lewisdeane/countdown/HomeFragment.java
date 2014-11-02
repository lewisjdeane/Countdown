package uk.me.lewisdeane.countdown;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    private Button mStandardButton, mTimeButton;

    private View mRootView;

    private HashMap<Button, MainActivity.GAME_TYPE> mButtonMap = new HashMap<Button, MainActivity.GAME_TYPE>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_home, container, false);

        mStandardButton = (Button) mRootView.findViewById(R.id.fragment_home_standard);
        mTimeButton = (Button) mRootView.findViewById(R.id.fragment_home_time);

        mButtonMap.put(mStandardButton, MainActivity.GAME_TYPE.STANDARD);
        mButtonMap.put(mTimeButton, MainActivity.GAME_TYPE.TIME);

        mStandardButton.setOnClickListener(getHomeButtonListener());
        mTimeButton.setOnClickListener(getHomeButtonListener());

        return mRootView;
    }

    private View.OnClickListener getHomeButtonListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMode(mButtonMap.get((Button)view));
            }
        };

        return listener;
    }

    private void loadMode(MainActivity.GAME_TYPE _gameType){
        Log.i("K DEN", _gameType+"");

        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.PARAM_GAME_MODE, _gameType+"");
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_container, fragment);
        fragmentTransaction.commit();

        /*
        Fragment fragment = new GameFragment().newInstance(_gameType);
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_container, fragment);
        fragmentTransaction.commit();
        */
    }
}
