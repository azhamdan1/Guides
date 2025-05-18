package com.example.myapplication.Guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.myapplication.HomeFragment;
import com.example.myapplication.R;
import com.example.myapplication.Servicies.FirebaseServices;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuideDetailesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideDetailesFragment extends Fragment {
    private FirebaseServices fbs;
    private ImageView ivPhoto;
    private TextView tvTitle, tvDescription, tvPhoneNumber;
    private Guide Guide;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuideDetailesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuideDetailesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuideDetailesFragment newInstance(String param1, String param2) {
        GuideDetailesFragment fragment = new GuideDetailesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide_detailes, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {
        fbs = new FirebaseServices().getInstance();
        ivPhoto = getActivity().findViewById(R.id.ivPhotoGuideDetails);
        tvTitle = getActivity().findViewById(R.id.tvTitleGuideDetails);
        tvDescription = getActivity().findViewById(R.id.tvDescriptionGuideDetails);
        tvPhoneNumber = getActivity().findViewById(R.id.tvPhoneNumberGuideDetails);

        Bundle args = getArguments();
        if (args != null) {
            Guide = args.getParcelable("guides");
            if (Guide != null) {
                tvTitle.setText(Guide.getName());
                tvDescription.setText(Guide.getDescription());
                tvPhoneNumber.setText(Guide.getPhoneNumber());
                if (Guide.getImageUrl() == null || Guide.getImageUrl().isEmpty()) {
                    Picasso.get().load(R.drawable.ic_menu_gallery).into(ivPhoto);
                } else {
                    Picasso.get().load(Guide.getImageUrl()).into(ivPhoto);
                }
            }
        }
    }
    private void gotoHomeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain,new HomeFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}