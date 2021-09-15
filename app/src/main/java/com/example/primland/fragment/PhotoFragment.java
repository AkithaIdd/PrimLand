package com.example.primland.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.primland.R;
import com.example.primland.model.CustomerInfo;
import com.example.primland.model.CustomerInfoVIewModel;
import com.example.primland.model.CustomerPhoto;
import com.example.primland.model.CustomerPhotoViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;


public class PhotoFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 10;
    static final int REQUEST_GALLERY_CAPTURE = 20;
    private static final int GALLERY_CODE = 100;
    private static final int PICK_IMAGE = 100;
    ImageView imageView, image;
    private Uri uri;
    private CustomerPhotoViewModel customerPhotoViewModel;
    private CustomerInfoVIewModel customerInfoVIewModel;
    private long code;


    public PhotoFragment() {
        // Required empty public constructor
    }


    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageView = view.findViewById(R.id.image);
        image = view.findViewById(R.id.imageView);
        customerPhotoViewModel  = new ViewModelProvider(this).get(CustomerPhotoViewModel.class);
        customerInfoVIewModel  = new ViewModelProvider(this).get(CustomerInfoVIewModel.class);

        customerInfoVIewModel.getAllCustomers().observe(this,customerInfo -> {
//            StringBuilder builder = new StringBuilder();

            for (CustomerInfo customerInfo1 : customerInfo){

                code = customerInfo1.getCode();

                Log.d("code", "onCreate: "+ customerInfo1.getCode());
            }


        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(
                            new String[]{READ_EXTERNAL_STORAGE},REQUEST_GALLERY_CAPTURE
                    );


                }else {
                    openGallery();
                }





            }
        });

        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                saveImageToExternal(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            image.setImageURI(uri);
        }
    }

    private void saveImageToExternal(Bitmap bitmap) {
        String imgName = "image".concat(String.valueOf(System.currentTimeMillis()));
        //Create Path to save Image
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //Creates app specific folder
        path.mkdirs();
        File imageFile = new File(path, imgName + ".png"); // Imagename.png
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, out); // Compress Image
            out.flush();
            out.close();
            Log.d("t", "onActivityResult: " + imageFile.toString());
            CustomerPhoto customerPhoto = new CustomerPhoto(code,imageFile.toString());
            CustomerPhotoViewModel.insertPhoto(customerPhoto);

            //         saveOrUpdateCustomer(imageFile.getAbsolutePath()); //save image detail local database

        } catch (Exception e) {

        }


//    private void saveOrUpdateCustomer(String absolutePath) {
//        TblCustomerImage rmCustomerImage = new TblCustomerImage();
//
//        rmCustomerImage.setId(System.currentTimeMillis());
//        rmCustomerImage.setCustomerId(((CustomerInfoActivity)getActivity()).primaryKey);
//        rmCustomerImage.setPath(path);
//        try {
//            BaseActivity.primeDatabase.customerImageDao().addCustomerImage(rmCustomerImage);
//            helper.showSuccessMessage(getActivity(),getString(R.string.image_upload_success));
//
//        }catch (Exception e){
//            e.printStackTrace();
//            helper.showErrorMessage(getActivity(),getString(R.string.image_upload_fail));
//        }
//        displayLocalImages();
//    }

    }
}
