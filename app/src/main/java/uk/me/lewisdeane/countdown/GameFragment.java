package uk.me.lewisdeane.countdown;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment {

    private static MainActivity.GAME_TYPE mGameType;

    public static GameFragment newInstance(MainActivity.GAME_TYPE _gameType) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.PARAM_GAME_MODE, _gameType+"");
        fragment.setArguments(args);
        return fragment;
    }
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }
}
