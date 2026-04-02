package com.example.ecommerceapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// The entry point for Hilt's DI
@HiltAndroidApp
class EcommerceApp : Application() {

// @HiltAndroidApp triggers the generation of:
// 1- Hilt Component
// 2- DI container

// @HiltAndroidApp initializes Hilt in your app

}