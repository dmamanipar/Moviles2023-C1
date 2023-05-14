<?php


namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;


class Persona extends Model
{
    use HasFactory;
    protected $table = 'personas';


    public $timestamps = false;


    protected $primaryKey = 'id';


    protected $fillable = ['id', 'dni', 'nombre', 'apellido_paterno', 'apellido_materno',
    "telefono",'genero', 'correo'];
}
