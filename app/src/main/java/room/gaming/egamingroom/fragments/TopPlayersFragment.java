package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.helper.MyApiRequest;
import room.gaming.egamingroom.helper.MyCallback;
import room.gaming.egamingroom.helper.MyConfig;
import room.gaming.egamingroom.helper.MyGson;
import room.gaming.egamingroom.helper.MyUrlConnection;
import room.gaming.egamingroom.models.TopPlayerDto;

public class TopPlayersFragment extends Fragment {
   ListView listTopPlayers;
    public static TopPlayersFragment newInstance() {
      return  new TopPlayersFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
  View view = inflater.inflate(R.layout.top_players_layout, container, false);
  listTopPlayers = view.findViewById(R.id.top_players_list_id);

getTopPlayers();
  return  view;
    }

    private void getTopPlayers() {
        MyCallback<ArrayList<TopPlayerDto>> myCallback = new MyCallback<ArrayList<TopPlayerDto>>(new TypeToken<ArrayList<TopPlayerDto>>(){}.getType()) {
            @Override
            public void Run(ArrayList<TopPlayerDto> x) {
                fillTopPlayersData(x);
            }
        };
        MyApiRequest.get(getActivity(), "api/User/topplayers", myCallback, true);
    }
    private void fillTopPlayersData(List<TopPlayerDto> data) {
        listTopPlayers.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if(view == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.top_player_item, parent, false);
                }

                TextView firstName = view.findViewById(R.id.top_player_firstName_id);
                TextView lastName = view.findViewById(R.id.top_player_lastName_id);
                TextView coins = view.findViewById(R.id.top_players_coins_id);

                TopPlayerDto x = data.get(position);

                firstName.setText(x.user.firstName);
                lastName.setText(x.user.lastName);
                coins.setText(String.valueOf(x.coins));
return view;
            }
        });

    }
}
