// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'database.dart';

// **************************************************************************
// FloorGenerator
// **************************************************************************

// ignore: avoid_classes_with_only_static_members
class $FloorAppDatabase {
  /// Creates a database builder for a persistent database.
  /// Once a database is built, you should keep a reference to it and re-use it.
  static _$AppDatabaseBuilder databaseBuilder(String name) =>
      _$AppDatabaseBuilder(name);

  /// Creates a database builder for an in memory database.
  /// Information stored in an in memory database disappears when the process is killed.
  /// Once a database is built, you should keep a reference to it and re-use it.
  static _$AppDatabaseBuilder inMemoryDatabaseBuilder() =>
      _$AppDatabaseBuilder(null);
}

class _$AppDatabaseBuilder {
  _$AppDatabaseBuilder(this.name);

  final String? name;

  final List<Migration> _migrations = [];

  Callback? _callback;

  /// Adds migrations to the builder.
  _$AppDatabaseBuilder addMigrations(List<Migration> migrations) {
    _migrations.addAll(migrations);
    return this;
  }

  /// Adds a database [Callback] to the builder.
  _$AppDatabaseBuilder addCallback(Callback callback) {
    _callback = callback;
    return this;
  }

  /// Creates the database and initializes it.
  Future<AppDatabase> build() async {
    final path = name != null
        ? await sqfliteDatabaseFactory.getDatabasePath(name!)
        : ':memory:';
    final database = _$AppDatabase();
    database.database = await database.open(
      path,
      _migrations,
      _callback,
    );
    return database;
  }
}

class _$AppDatabase extends AppDatabase {
  _$AppDatabase([StreamController<String>? listener]) {
    changeListener = listener ?? StreamController<String>.broadcast();
  }

  ActividadDao? _actividadDaoInstance;

  Future<sqflite.Database> open(
    String path,
    List<Migration> migrations, [
    Callback? callback,
  ]) async {
    final databaseOptions = sqflite.OpenDatabaseOptions(
      version: 1,
      onConfigure: (database) async {
        await database.execute('PRAGMA foreign_keys = ON');
        await callback?.onConfigure?.call(database);
      },
      onOpen: (database) async {
        await callback?.onOpen?.call(database);
      },
      onUpgrade: (database, startVersion, endVersion) async {
        await MigrationAdapter.runMigrations(
            database, startVersion, endVersion, migrations);

        await callback?.onUpgrade?.call(database, startVersion, endVersion);
      },
      onCreate: (database, version) async {
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `actividad` (`id` INTEGER NOT NULL, `periodo_id` INTEGER NOT NULL, `nombre_actividad` TEXT NOT NULL, `fecha` TEXT NOT NULL, `horai` TEXT NOT NULL, `min_toler` TEXT NOT NULL, `latitud` TEXT NOT NULL, `longitud` TEXT NOT NULL, `estado` TEXT NOT NULL, `evaluar` TEXT NOT NULL, `user_create` TEXT NOT NULL, PRIMARY KEY (`id`))');

        await callback?.onCreate?.call(database, version);
      },
    );
    return sqfliteDatabaseFactory.openDatabase(path, options: databaseOptions);
  }

  @override
  ActividadDao get actividadDao {
    return _actividadDaoInstance ??= _$ActividadDao(database, changeListener);
  }
}

class _$ActividadDao extends ActividadDao {
  _$ActividadDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database, changeListener),
        _actividadModeloInsertionAdapter = InsertionAdapter(
            database,
            'actividad',
            (ActividadModelo item) => <String, Object?>{
                  'id': item.id,
                  'periodo_id': item.periodoId,
                  'nombre_actividad': item.nombreActividad,
                  'fecha': item.fecha,
                  'horai': item.horai,
                  'min_toler': item.minToler,
                  'latitud': item.latitud,
                  'longitud': item.longitud,
                  'estado': item.estado,
                  'evaluar': item.evaluar,
                  'user_create': item.userCreate
                },
            changeListener),
        _actividadModeloUpdateAdapter = UpdateAdapter(
            database,
            'actividad',
            ['id'],
            (ActividadModelo item) => <String, Object?>{
                  'id': item.id,
                  'periodo_id': item.periodoId,
                  'nombre_actividad': item.nombreActividad,
                  'fecha': item.fecha,
                  'horai': item.horai,
                  'min_toler': item.minToler,
                  'latitud': item.latitud,
                  'longitud': item.longitud,
                  'estado': item.estado,
                  'evaluar': item.evaluar,
                  'user_create': item.userCreate
                },
            changeListener),
        _actividadModeloDeletionAdapter = DeletionAdapter(
            database,
            'actividad',
            ['id'],
            (ActividadModelo item) => <String, Object?>{
                  'id': item.id,
                  'periodo_id': item.periodoId,
                  'nombre_actividad': item.nombreActividad,
                  'fecha': item.fecha,
                  'horai': item.horai,
                  'min_toler': item.minToler,
                  'latitud': item.latitud,
                  'longitud': item.longitud,
                  'estado': item.estado,
                  'evaluar': item.evaluar,
                  'user_create': item.userCreate
                },
            changeListener);

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<ActividadModelo> _actividadModeloInsertionAdapter;

  final UpdateAdapter<ActividadModelo> _actividadModeloUpdateAdapter;

  final DeletionAdapter<ActividadModelo> _actividadModeloDeletionAdapter;

  @override
  Future<List<ActividadModelo>> listarActividad() async {
    return _queryAdapter.queryList('SELECT * FROM actividad',
        mapper: (Map<String, Object?> row) => ActividadModelo(
            id: row['id'] as int,
            periodoId: row['periodo_id'] as int,
            nombreActividad: row['nombre_actividad'] as String,
            fecha: row['fecha'] as String,
            horai: row['horai'] as String,
            minToler: row['min_toler'] as String,
            latitud: row['latitud'] as String,
            longitud: row['longitud'] as String,
            estado: row['estado'] as String,
            evaluar: row['evaluar'] as String,
            userCreate: row['user_create'] as String));
  }

  @override
  Stream<ActividadModelo?> buscarActividadId(int id) {
    return _queryAdapter.queryStream('SELECT * FROM actividad where id=?1',
        mapper: (Map<String, Object?> row) => ActividadModelo(
            id: row['id'] as int,
            periodoId: row['periodo_id'] as int,
            nombreActividad: row['nombre_actividad'] as String,
            fecha: row['fecha'] as String,
            horai: row['horai'] as String,
            minToler: row['min_toler'] as String,
            latitud: row['latitud'] as String,
            longitud: row['longitud'] as String,
            estado: row['estado'] as String,
            evaluar: row['evaluar'] as String,
            userCreate: row['user_create'] as String),
        arguments: [id],
        queryableName: 'actividad',
        isView: false);
  }

  @override
  Future<void> insertarActividad(ActividadModelo actividad) async {
    await _actividadModeloInsertionAdapter.insert(
        actividad, OnConflictStrategy.replace);
  }

  @override
  Future<void> updateActividad(ActividadModelo actividad) async {
    await _actividadModeloUpdateAdapter.update(
        actividad, OnConflictStrategy.abort);
  }

  @override
  Future<void> deleteActividad(ActividadModelo actividad) async {
    await _actividadModeloDeletionAdapter.delete(actividad);
  }
}
