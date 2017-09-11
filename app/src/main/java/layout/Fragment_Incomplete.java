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
 * {@link Fragment_Incomplete.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Incomplete#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Incomplete extends Fragment {



    public Fragment_Incomplete() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Incomplete.
     */
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

        String [] incompleteTasks={"Task 5","Task 6","Task 7","Task 8"};

        ListAdapter inCompleteTasksAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,incompleteTasks);

        View myFragmentView = inflater.inflate(R.layout.fragment_fragment__incomplete, container, false);

        ListView inCompleteListView = (ListView) myFragmentView.findViewById(R.id.incompleteList);


        inCompleteListView.setAdapter(inCompleteTasksAdapter);


        return myFragmentView;
    }


}
