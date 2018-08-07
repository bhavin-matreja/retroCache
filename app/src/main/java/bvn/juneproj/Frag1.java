package bvn.juneproj;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bhavin on 7/6/2018.
 */
public class Frag1 extends Fragment {

    private OnClickListener clickListener;

    public interface OnClickListener{
        void onClickedd();
    }

    @Override
    public void onAttach(Context context) {
        try {
            clickListener = (OnClickListener) context;
        }
        catch (Exception e){
            throw new ClassCastException("Must Implement OnClickListener");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        return view;
    }

}
