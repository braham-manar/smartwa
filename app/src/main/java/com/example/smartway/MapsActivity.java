package com.example.smartway;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartway.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
   Location currentLocation;
   FusedLocationProviderClient fusedLocationProviderClient;
   private static final int REQUEST_CODE = 101; // l'identifiant de l'appel de l'autorisation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Fournisseur d'emplacement fusionné pour récuperer le dernier emplacemet connu de l'appareil
        //API de localisation des services Google play

        fusedLocationProviderClient = LocationServices.
                getFusedLocationProviderClient(this);
        fetchLocation();
    }
    private void fetchLocation() {
        //verifier est ce que l'application est autorisée le dernier emplacement connu de l'appareil
        if (ActivityCompat.checkSelfPermission(
                this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                        this,Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE);
            return;

        }
        //autorisation déja accordé on obtient le dernier emplacement
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener((OnSuccessListener) ( location)->{
        if( location != null){
           currentLocation = (Location) location;
            Toast.makeText(getBaseContext(),currentLocation.getLatitude()+""
                    +currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map) ;
            assert supportMapFragment != null;
            supportMapFragment.getMapAsync(MapsActivity.this);      }
        }
    );}
    @Override
    public void onMapReady (GoogleMap  googleMap ){
        //créer un objet latLng qui stocke la latitude et la longitude de la localisation actuelle
        LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        //ajouter un marker de la localisation actuelle dans la carte
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("je suis là");
        googleMap.animateCamera (CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
        googleMap.addMarker(markerOptions);}


    public void onRequestPermissionResult(int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults) {
    switch ( requestCode){
        case REQUEST_CODE:
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                fetchLocation();}
            break;



    }}}

