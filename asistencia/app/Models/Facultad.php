<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Facultad extends Model
{
    use HasFactory;

    protected $table = 'facultads';
    public $timestamps = true;
    protected $primaryKey = 'id';
    protected $fillable = [
        'id', 'nombrefac','estado','iniciales'
    ];

    public function escuelas()
    {
        return $this->hasMany(Escuela::class);
    }
}
