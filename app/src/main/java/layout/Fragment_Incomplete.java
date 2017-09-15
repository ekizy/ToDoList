package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yusuf.todolist.DBHelper;
import com.example.yusuf.todolist.IncompleteTaskAdapter;
import com.example.yusuf.todolist.NewTaskActivity;
import com.example.yusuf.todolist.R;
import com.example.yusuf.todolist.Task;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_Incomplete extends Fragment {


    FloatingActionButton addButton;

    public Fragment_Incomplete() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_Incomplete newInstance() {
        Fragment_Incomplete fragment = new Fragment_Incomplete();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myFragmentView = inflater.inflate(R.layout.fragment_fragment__incomplete, container, false);



        DBHelper dbHelper = new DBHelper(getActivity());

        List<Task> tasks = dbHelper.getInCompleteTasks();

        ListView inCompleteListView=(ListView) myFragmentView.findViewById(R.id.incompleteList);

        IncompleteTaskAdapter customAdapter = new IncompleteTaskAdapter(getActivity(),tasks,getContext());

        inCompleteListView.setAdapter(customAdapter);

        addButton = (FloatingActionButton) myFragmentView.findViewById(R.id.button_add_task);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getContext().getApplicationContext(),NewTaskActivity.class);
                getActivity().startActivity(newIntent);
            }
        });

        return myFragmentView;
    }

    public Timestamp convertDateToTimestamp(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date tempDate = null;
        try {
            tempDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Timestamp(tempDate.getTime());
    }


}
