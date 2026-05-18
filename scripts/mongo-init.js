db = db.getSiblingDB('championship');

db.createUser({
    user: 'championship-app',
    pwd: 'championship-secret',
    roles: [{ role: 'readWrite', db: 'championship' }]
});

const championshipId = "a1b2c3d4-e5f6-7890-abcd-ef1234567890";

db.championships.insertOne({
    _id: championshipId,
    name: "Ligue 1 2026-2027",
    startDate: new Date("2026-08-01"),
    endDate: new Date("2027-05-31"),
    status: "PLANNED"
});

db.matches.insertMany([
    {
        _id: "11111111-1111-1111-1111-111111111111",
        championshipId: championshipId,
        homeTeam: "Paris Saint-Germain",
        awayTeam: "Olympique Lyonnais",
        scheduledAt: new Date("2026-08-10")
    },
    {
        _id: "22222222-2222-2222-2222-222222222222",
        championshipId: championshipId,
        homeTeam: "Olympique de Marseille",
        awayTeam: "AS Monaco",
        scheduledAt: new Date("2026-08-17")
    },
    {
        _id: "33333333-3333-3333-3333-333333333333",
        championshipId: championshipId,
        homeTeam: "Olympique Lyonnais",
        awayTeam: "Olympique de Marseille",
        scheduledAt: new Date("2026-08-24")
    }
]);
