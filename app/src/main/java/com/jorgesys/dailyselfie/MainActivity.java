package com.jorgesys.dailyselfie;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jorgesys2.dailyselfie.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private PendingIntent pendingIntent;
	private SelfieFragment mSelfieFragment;
	private static final int INTERVAL = 60000;
	private static final int CODE_IMAGE_CAPTURE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSelfieFragment = new SelfieFragment();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, mSelfieFragment).commit();
		}
        setupAlarm();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.new_selfie) {
			dispatchTakePictureIntent();
			return true;
		} else if (id == R.id.delete_all) {
			deleteAllSelfies();
			return true;
		} /*else if (id == R.id.delete_selfie) {
			deleteSelfie();
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void finish() {
		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 builder.setTitle(R.string.app_name);
		 builder.setIcon(R.drawable.ic_launcher);
		 builder.setMessage(R.string.quit_message);
		 builder.setPositiveButton(R.string.positive_button, 
	      new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	          quit();
				
	         }
	      });
		 builder.setNegativeButton(R.string.negative_button, 
	      new DialogInterface.OnClickListener() {
				
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	           //No Action
			 }
	      });
	      AlertDialog alertDialog = builder.create();
	      alertDialog.show();
		
	};
	
	private void quit(){
		super.finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CODE_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        String selfieName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());	        
	        Selfie newSelfie = new Selfie(selfieName, null);
	        newSelfie.setPictureBitmap(SelfiePictureHelper.getTempBitmap(this));
	        mSelfieFragment.addSelfie(newSelfie);
	    }
	}
	
	/**
	 * Setup a repeating alarm with an interval of two minutes
	 */
	private void setupAlarm() {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + INTERVAL, INTERVAL, pendingIntent);
	}
	
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(SelfiePictureHelper.getTempFile(this))); 
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, CODE_IMAGE_CAPTURE);
	    }
	}
	
	private void deleteAllSelfies() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.app_name);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage(getString(R.string.delete_all));
		builder.setPositiveButton(getString(R.string.yes), dialogDeleteAllSelfiesClickListener);
		builder.setNegativeButton(getString(R.string.no), dialogDeleteAllSelfiesClickListener).show();
	}
	
	/*
	private void deleteSelfie() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage(getString(R.string.delete_selfie));
		builder.setPositiveButton(getString(R.string.yes), dialogDeleteSelfieClickListener);
		builder.setNegativeButton(getString(R.string.no), dialogDeleteSelfieClickListener).show();
	}*/
	
	DialogInterface.OnClickListener dialogDeleteAllSelfiesClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	        	mSelfieFragment.deleteAllSelfies();
	            break;
	        case DialogInterface.BUTTON_NEGATIVE:
	            break;
	        }
	    }
	};
	
	/*DialogInterface.OnClickListener dialogDeleteSelfieClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	        	mSelfiesFragment.deleteSelectedSelfie();
	        	getFragmentManager().popBackStack();
	            break;
	        case DialogInterface.BUTTON_NEGATIVE:
	            break;
	        }
	    }
	};*/
	
	

}