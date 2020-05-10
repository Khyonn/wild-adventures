export const environment = {
    production: true,

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
