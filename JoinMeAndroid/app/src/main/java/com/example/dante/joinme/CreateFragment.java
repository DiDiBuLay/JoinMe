package com.example.dante.joinme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.JoinMe.event;
import com.cloud.JMCloud;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private static EditText mTitle, mTime, mLocation, mPeople, mDescription;

    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance() {
        CreateFragment fragment = new CreateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CreateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_create, container, false);
        initialComponent(layout);
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void initialComponent(View view) {
        mTitle = (EditText) view.findViewById(R.id.create_fragment_title);
        mTime = (EditText) view.findViewById(R.id.create_fragment_time);
        mLocation = (EditText) view.findViewById(R.id.create_fragment_location);
        mPeople = (EditText) view.findViewById(R.id.create_fragment_people);
        mDescription = (EditText) view.findViewById(R.id.create_fragment_description);
    }

    public static event getNewEvent() {
        Log.e("lala", "getNewEvent");
        event event = new event();
        event.setTitle(mTitle.getText().toString() == null ? "" : mTitle.getText().toString());
        event.setTime(mTime.getText().toString() == null ? "" : mTime.getText().toString());
        Random r = new Random();
        double randomValue = -5 + (100 - (-5)) * r.nextDouble();
        event.setLatitude(randomValue);
        double randomValue2 = -5 + (100 - (-5)) * r.nextDouble();
        event.setLongitude(randomValue2);
        event.setContent(mDescription.getText().toString() == null ? "" : mDescription.getText().toString());
        event.setEstimate_num(Integer.parseInt(mPeople.getText() == null ? "5" : mPeople.getText().toString()));
        event.setOwner_id(JMCloud.USER_ID);
        return event;
    }
}
