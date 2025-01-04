import http from 'k6/http';
import { check, sleep } from 'k6';

// Configuration des scénarios
export let options = {
    vus: 10, // Nombre d'utilisateurs virtuels
    duration: '30s', // Durée du test
};

export default function () {
    // Effectuer une requête GET
    let res = http.get('http://localhost:8080/api/users');

    // Vérifier les réponses
    check(res, {
        'status est 200': (r) => r.status === 200,
        'la réponse contient User': (r) => r.body.includes('User'),
    });

    sleep(1); // Pause d'une seconde entre les requêtes
}
