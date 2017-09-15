package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yusuf.todolist.CompleteTaskAdapter;
import com.example.yusuf.todolist.DBHelper;
import com.example.yusuf.todolist.R;
import com.example.yusuf.todolist.Task;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Complete.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Complete#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Complete extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public Fragment_Complete() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Complete.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Complete newInstance() {
        Fragment_Complete fragment = new Fragment_Complete();
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

        View myFragmentView = inflater.inflate(R.layout.fragment_fragment__complete, container, false);

        DBHelper dbHelper = new DBHelper(getActivity());

        List<Task> tasks = dbHelper.getCompletedTasks();

        ListView completedListView=(ListView) myFragmentView.findViewById(R.id.completeList);

        CompleteTaskAdapter customAdapter = new CompleteTaskAdapter(getActivity(),tasks,getContext());

        completedListView.setAdapter(customAdapter);

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
