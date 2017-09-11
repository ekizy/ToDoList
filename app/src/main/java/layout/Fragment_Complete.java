package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yusuf.todolist.R;

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



        String [] completedTasks={"Task 1"," Task 2"," Task 3"," Task 4"};

        ListAdapter completedTasksAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,completedTasks);

        View myFragmentView = inflater.inflate(R.layout.fragment_fragment__complete, container, false);

        ListView completedListView=(ListView) myFragmentView.findViewById(R.id.completeList);
        completedListView.setAdapter(completedTasksAdapter);

        return myFragmentView;
    }




}
