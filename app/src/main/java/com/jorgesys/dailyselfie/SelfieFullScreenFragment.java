package com.jorgesys.dailyselfie;

import com.jorgesys2.dailyselfie.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SelfieFullScreenFragment extends Fragment {
	
	private Selfie mSelfie;
	private SelfieDatabase mSelfiesDatabase;
	
	public SelfieFullScreenFragment(Selfie selfie) {
		mSelfie = selfie;
		/*mSelfiesDatabase = new SelfiesDatabase(getActivity());
		mSelfiesDatabase.open();*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
		setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.selfie_fragment, container, false);
		ImageView imageView = (ImageView)view.findViewById(R.id.selfie_details_picture_image_view);
		imageView.setImageBitmap(SelfiePictureHelper.getScaledBitmap(mSelfie.getPicturePath(), 0, 0));
	    getActivity().getActionBar().setTitle(mSelfie.getName());
        return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		//inflater.inflate(R.menu.details_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		 if (id == R.id.delete_selfie) {
			deleteSelfie();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void deleteSelfie() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle(R.string.app_name);
		builder.setMessage(getString(R.string.delete_selfie));
		builder.setPositiveButton(getString(R.string.yes), dialogDeleteSelfieClickListener);
		builder.setNegativeButton(getString(R.string.no), dialogDeleteSelfieClickListener).show();
	}
	
	DialogInterface.OnClickListener dialogDeleteSelfieClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	        	//mSelfiesFragment.deleteSelectedSelfie();
	        	deleteSelectedSelfie();
	        	getFragmentManager().popBackStack();
	            break;
	        case DialogInterface.BUTTON_NEGATIVE:
	            break;
	        }
	    }
	};
	
	public void deleteSelectedSelfie() {
		//	mSelfiesDatabase.deleteSelfie(mSelfie.getID());
	}
}
