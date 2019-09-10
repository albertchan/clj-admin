# clj-admin

A demo user management server written in Clojure.

## Features

- [HTTP Kit](http://www.http-kit.org/) minimalist, efficient, Ring-compatible HTTP server for Clojure
- [mount](https://github.com/tolitius/mount) for managing app state
- [Ragtime](https://github.com/weavejester/ragtime) for database migrations
- [reitit](https://github.com/metosin/reitit) for routing

## Prerequisites

- You will need [Leiningen][] 2.0.0 or above installed. [leiningen]: https://github.com/technomancy/leiningen
- PostgreSQL is used for the data store.

## Running

Start the server by running:

```
$ lein run -m clj-admin.core
```

The API endpoints can now be reached at [http://localhost:3000/api/v1](http://localhost:3000/api/v1).

## Development

PostgreSQL database setup:

```
$ createdb clj_admin_dev
```

The extension `uuid-ossp` must be installed on the database as UUIDs are auto-generated with `uuid_generate_v4()` for all `id` columns. So after connecting to the database in `psql` run:

```
psql=# CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

Run migrations:

```
$ lein migrate
```

Rollback migrations:

```
$ lein rollback
```

Creating migrations:

```
$ NOW=$(date "+%Y%m%d%H%M%S")
$ touch resources/migrations/$NOW-create-users.up.sql
$ touch resources/migrations/$NOW-create-users.down.sql
```

## TODOs

- [ ] Tests
- [ ] Exception handling
- [ ] Add authentication
- [ ] Document API with Swagger (reitit.swagger)
- [ ] Possibly add [Toucan](https://github.com/metabase/toucan) for ORM support

## License

Copyright © 2019 Albert Chan
