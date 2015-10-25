package com.example.dante.joinme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.JoinMe.event;

public class DefaultFragment extends Fragment {
    private static final String KEY_EVENT = "event";

    private OnFragmentInteractionListener mListener;

    private static event sEvent;
    private static TextView sTitle, sTime, sLocation, sPeople, sDescription;

    // TODO: Rename and change types and number of parameters
    public static DefaultFragment newInstance(event event) {
        DefaultFragment fragment = new DefaultFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_EVENT, event);
        fragment.setArguments(args);
        return fragment;
    }

    public DefaultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sEvent = (event) getArguments().getSerializable(KEY_EVENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_default, container, false);
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
        sTitle = (TextView) view.findViewById(R.id.default_fragment_title);
        sTime = (TextView) view.findViewById(R.id.default_fragment_time);
        sLocation = (TextView) view.findViewById(R.id.default_fragment_location);
        sPeople = (TextView) view.findViewById(R.id.default_fragment_people);
        sDescription = (TextView) view.findViewById(R.id.default_fragment_description);

        if (sEvent != null) {
            sTitle.setText(sEvent.getTitle());
            sTime.setText(sEvent.getTime());
            sLocation.setText("( " + sEvent.getLatitude() + " , " + sEvent.getLongitude() + " )");
            sPeople.setText(sEvent.getEstimate_num() + "");
            sDescription.setText(sEvent.getContent());
        }
    }

    public static void updateUi(event event) {
        try {
            Log.e("lala", "start");
            sEvent = event;
            if (sEvent != null) {
                Log.e("lala", "success");
                sTitle.setText(sEvent.getTitle());
                sTime.setText(sEvent.getTime());
                sLocation.setText("( " + sEvent.getLatitude() + " , " + sEvent.getLongitude() + " )");
                sPeople.setText(sEvent.getEstimate_num() + "");
                sDescription.setText(sEvent.getContent());
            } else {
                Log.e("lala", "sEvent is null.");
            }
        } catch (NullPointerException e) {
            Log.e("lala", "failed");
            // I don't want to do anyThingXD
        }
    }
}