package room.gaming.egamingroom.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import room.gaming.egamingroom.R;
import room.gaming.egamingroom.models.Payment;
import room.gaming.egamingroom.models.TopPlayerDto;
import room.gaming.egamingroom.models.User;

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

  fillTopPlayersData();
  return  view;
    }

    private void fillTopPlayersData() {
        final List<TopPlayerDto> data = new ArrayList<>();
          TopPlayerDto t = new TopPlayerDto();
          User  u = new User();
          u.FirstName = "test";
          u.LastName = "test";
          t.Coins = 100;
          t.User = u;

        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);
        data.add(t);

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

                firstName.setText(x.User.FirstName);
                lastName.setText(x.User.LastName);
                coins.setText(String.valueOf(x.Coins));
return view;
            }
        });

    }
}
