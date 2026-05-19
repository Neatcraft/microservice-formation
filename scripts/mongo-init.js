rs.initiate({
    _id: "rs0",
    members: [{ _id: 0, host: "localhost:27017" }]
});

while (!rs.hello().isWritablePrimary) {
    sleep(500);
}

const championship = db.getSiblingDB('championship');

championship.createCollection('championships');
championship.createCollection('matches');

const championshipId = "a1b2c3d4-e5f6-7890-abcd-ef1234567890";

championship.championships.insertOne({
    _id: championshipId,
    name: "Ligue 1 2026-2027",
    startDate: new Date("2026-08-01"),
    endDate: new Date("2027-05-31"),
    status: "PLANNED"
});

championship.matches.insertMany([
    {
        _id: "566aa3c8-c09d-4922-ae8c-4e49912e9444",
        championshipId: championshipId,
        homeTeam: "Paris Saint-Germain",
        awayTeam: "Olympique Lyonnais",
        scheduledAt: new Date("2026-08-10")
    },
    {
        _id: "9670bbf2-9179-4925-9abd-afc0a59584a7",
        championshipId: championshipId,
        homeTeam: "Olympique de Marseille",
        awayTeam: "AS Monaco",
        scheduledAt: new Date("2026-08-17")
    },
    {
        _id: "6ce2d4e4-8e55-4ba4-ba48-8d04df7efb12",
        championshipId: championshipId,
        homeTeam: "Olympique Lyonnais",
        awayTeam: "Olympique de Marseille",
        scheduledAt: new Date("2026-08-24")
    }
]);
