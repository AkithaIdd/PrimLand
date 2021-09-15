package com.example.primland.util;

import android.content.Context;
import android.util.Log;

import com.example.primland.data.CustomerInfoDao;
import com.example.primland.data.CustomerPhotoDao;
import com.example.primland.model.CustomerInfo;
import com.example.primland.model.CustomerPhoto;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Akitha Iddamalgoda on 8/13/2021.
 **/
@Database(entities = {CustomerInfo.class , CustomerPhoto.class},version = 1,exportSchema = false)
public abstract class CustomerInfoRoomDB extends RoomDatabase {



    public static final int NUMBER_OF_THREADS = 10;

    //nul instance for singleton

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile CustomerInfoRoomDB INSTANCE;

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                CustomerInfoDao customerInfoDao = INSTANCE.customerInfoDao();
                customerInfoDao.deteleAll();

            });

            databaseWriteExecutor.execute(()->{
                CustomerPhotoDao customerPhotoDao = INSTANCE.customerPhotoDao();
                customerPhotoDao.deteleAll();

                CustomerPhoto customerPhoto = new CustomerPhoto(12,"/storage/emulated/0/Pictures/cus1629736040717.png");
                Log.d("db", "onCreate: "+customerPhoto.getPath());
                customerPhotoDao.addCustomerImage(customerPhoto);

            });
        }
    };

    public static CustomerInfoRoomDB getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (CustomerInfoRoomDB.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CustomerInfoRoomDB.class, "customer_info_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CustomerInfoDao customerInfoDao();
    public abstract CustomerPhotoDao customerPhotoDao();
}

//    public static CustomerInfoRoomDB getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            Log.d("null", "getDatabase: null1 ");
//            synchronized (CustomerInfoRoomDB.class){
//                if (INSTANCE == null) {
//                    Log.d("null", "getDatabase: null2 ");
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CustomerInfoRoomDB.class,"customer_info_db2")
//                            .addCallback(sRoomDatabaseCallback )
//                            .build();
//
//                }
//            }
//        }
//        return INSTANCE;
//    }

//    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            databaseWriteExecutor.execute(() -> {
//                CustomerInfoDao customerInfoDao = INSTANCE.customerInfoDao();
//                customerInfoDao.deteleAll();
////                Log.d("check", "onCreate: check");
////
//                CustomerInfo customerInfo = new CustomerInfo("akithaidd","qwe","test@me.com","veyangoda","test","ee");
//                Log.d("db", "onCreate: "+customerInfo.getName());
//                customerInfoDao.insert(customerInfo);
////
////                customerInfo = new CustomerInfo("akitha","qwe","test@me.com","veyangoda","test","ee");
////                Log.d("db", "onCreate: "+customerInfo.getName());
////                customerInfoDao.insert(customerInfo);
//
//            });
//        }
//    };
//}

//@Database(entities = {CustomerInfo.class},version = 1,exportSchema = false)
//public abstract class CustomerInfoRoomDB extends RoomDatabase {
//
//    public abstract CustomerInfoDao customerInfoDao();
//    public static final int NUMBER_OF_THREADS = 4;
//
//    //nul instance for singleton
//    private static volatile CustomerInfoRoomDB INSTANCE;
//    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    public static CustomerInfoRoomDB getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            Log.d("null", "getDatabase: null1 ");
//            synchronized (CustomerInfoRoomDB.class){
//                if (INSTANCE == null) {
//                    Log.d("null", "getDatabase: null2 ");
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CustomerInfoRoomDB.class,"customer_info_db2")
//                            .addCallback(sRoomDatabaseCallback )
//                            .build();
//
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            databaseWriteExecutor.execute(() -> {
//                CustomerInfoDao customerInfoDao = INSTANCE.customerInfoDao();
//                customerInfoDao.deteleAll();
////                Log.d("check", "onCreate: check");
////
//                CustomerInfo customerInfo = new CustomerInfo("akithaidd","qwe","test@me.com","veyangoda","test","ee");
//                Log.d("db", "onCreate: "+customerInfo.getName());
//                customerInfoDao.insert(customerInfo);
////
////                customerInfo = new CustomerInfo("akitha","qwe","test@me.com","veyangoda","test","ee");
////                Log.d("db", "onCreate: "+customerInfo.getName());
////                customerInfoDao.insert(customerInfo);
//
//            });
//        }
//    };
//}
