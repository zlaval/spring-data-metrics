import http from 'k6/http';
import { check, sleep } from 'k6';
import { jUnit, textSummary } from 'https://jslib.k6.io/k6-summary/0.0.2/index.js';

export const options = {
    stages: [
        { duration: '1m', target: 50 },  // Felfűtés: 0-ról 50 felhasználóra 1 perc alatt
        { duration: '2m', target: 50 },  // Tartás: 50 felhasználó 3 percig
        { duration: '1m', target: 200 }, // Terhelés emelése: 200 felhasználóra
        { duration: '3m', target: 200 }, // Tartás: 200 felhasználó
        { duration: '1m', target: 0 },   // Levezetés
    ],
    thresholds: {
        http_req_failed: ['rate<0.01'],   // A hibás kérések aránya legyen 1% alatt
        http_req_duration: ['p(95)<200'], // A kérések 95%-a legyen 200ms alatt
    },
};

const BASE_URL = 'http://localhost:8080/api/users';

export default function () {
    // 1. Véletlenszerű ID lekérése (1 és 10 millió között)
    const randomId = Math.floor(Math.random() * 10000000) + 1;

    const getRes = http.get(`${BASE_URL}/${randomId}`);
    check(getRes, {
        'get status is 200': (r) => r.status === 200,
        'has description': (r) => r.json().description !== null,
    });

    // Egy kis szünet a virtuális felhasználók kérései között, hogy ne legyen irreális
    sleep(0.1);

    // 2. Új user létrehozása (Write teszt)
    const payload = JSON.stringify({
        name: `K6_User_${__VU}_${__ITER}`,
        birthDate: '1990-01-01',
        description: 'Ez egy k6 teszt által generált leírás, ami elég hosszú kell legyen ahhoz, hogy az LZ4 tömörítés dolgozzon a háttérben...'
    });

    const params = { headers: { 'Content-Type': 'application/json' } };
    const postRes = http.post(BASE_URL, payload, params);

    check(postRes, {
        'post status is 201': (r) => r.status === 201,
    });

    sleep(0.1);
}

export function handleSummary(data) {
    return {
        'stdout': textSummary(data, { indent: ' ', enableColors: true }), // Kiírja a terminálba
        'summary.json': JSON.stringify(data),                            // Elmenti elemzéshez
    };
}