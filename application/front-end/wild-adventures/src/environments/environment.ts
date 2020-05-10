// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,

    stripe_key: 'pk_test_nnvjbufRknMJHBKwvQ9LnXMf00ZnwWS4Y2',

    keycloak_angular: {
        config: {
            clientId: 'wild-adventures-app',
            realm: 'wild-adventures',
            url: 'http://localhost:8081/auth',
        },
    },

    microservices: {
        adventures: 'ADVENTURES',
        comments: 'COMMENTS',
        reservations: 'RESERVATIONS',
    },
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
