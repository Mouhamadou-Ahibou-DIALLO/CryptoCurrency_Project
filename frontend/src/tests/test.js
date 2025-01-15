import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 10,
    duration: '30s',
    insecureSkipTLSVerify: true,
};

export default function () {
    let res = http.get('http://localhost:8080/api/users');

    if (res.status === 200 && res.body) {
        let isValid = res.body.includes('User');
        check(res, {
            'la réponse contient User': () => isValid
        });
    } else {
        console.error(`Erreur HTTP: ${res.status} ou réponse vide`);
    }

    check(res, {
        'status est 200': (r) => r.status === 200,
        'la réponse contient User': (r) => r.body && r.body.includes('User'),
    });

    sleep(1);
}
