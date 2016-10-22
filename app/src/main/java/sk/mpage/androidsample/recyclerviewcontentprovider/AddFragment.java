package sk.mpage.androidsample.recyclerviewcontentprovider;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import sk.mpage.androidsample.recyclerviewcontentprovider.db.ItemContentProvider;
import sk.mpage.androidsample.recyclerviewcontentprovider.db.NameItem;

/**
 * Created by maros on 22.10.2016.
 */
public class AddFragment extends Fragment {

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        final EditText editText = (EditText) view.findViewById(R.id.add_name);
        final RadioButton male = (RadioButton) view.findViewById(R.id.radio_male);

        Button button = (Button) view.findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                int gender = male.isChecked() ? 1 : 0;

                if (name.length()==0){
                    return;
                }
                NameItem item = new NameItem(name, gender);
                getActivity().getContentResolver().insert(ItemContentProvider.CONTENT_URI, item.getContentValues());
                Toast.makeText(getActivity(), name + " inserted.", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new RecyclerListFragment())
                        .commit();

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search){
            Toast.makeText(getActivity(), " inserted.", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new RecyclerListFragment())
                    .commit();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

}
