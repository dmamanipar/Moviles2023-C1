<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Periodo extends Model
{
    use HasFactory;
    protected $table = 'periodos';
    public $timestamps = false;
    protected $primaryKey = 'id';

    protected $fillable = [
        'id', 'nombre_periodo', 'estado',
    ];


}
