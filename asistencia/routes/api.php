<?php

//use App\Http\Controllers\AsistenciaController;
use App\Http\Controllers\ActividadController;
use App\Http\Controllers\PersonaController;
use App\Http\Controllers\AsisteciapaController;
use App\Http\Controllers\AuthController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['middleware' => 'api'], function () {
    Route::group(['prefix' => 'auth'], function () {
    Route::post('/login', [AuthController::class, 'login']);
    Route::post('/register', [AuthController::class, 'register']);
    });
    Route::post('/logout', [AuthController::class, 'logout']);
    Route::post('/refresh', [AuthController::class, 'refresh']);
   // Route::get('/profile', [AuthController::class, 'userProfile']);
    Route::resource('/act', ActividadController::class);
    //Route::resource('/asistencia', AsistenciaController::class);
    Route::resource('/asispa', AsisteciapaController::class);
    Route::resource('/persona', PersonaController::class);
    });

