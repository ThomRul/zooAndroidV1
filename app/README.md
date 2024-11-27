# Zoo App

## Description

Android example application for managing a zoo.

## Utilisation

### JSON Web Server

1.  Install JSON Web Server with npm: `npm install -g json-server`
2.  Create a `db.json` file.
3.  Paste the following JSON data into `db.json`:

```json
{
  "users": [
    {
      "id": "1",
      "username": "admin",
      "password": "admin123",
      "role": "ADMIN",
      "email": "admin@zoo.com"
    },
    {
      "id": "2",
      "username": "vet",
      "password": "vet123",
      "role": "VETERINARIAN",
      "email": "vet@zoo.com"
    },
    {
      "id": "3",
      "username": "keeper",
      "password": "keeper123",
      "role": "KEEPER",
      "email": "keeper@zoo.com"
    }
  ],
  "animals": [
    {
      "id": "1",
      "name": "Leo",
      "species": "Lion",
      "age": 5,
      "healthStatus": "Good",
      "lastCheckup": "2024-02-15",
      "assignedKeeperId": 3,
      "diet": "Carnivore",
      "medicalHistory": [
        {
          "date": "2024-01-10",
          "treatment": "Vaccination",
          "vetId": 2
        }
      ]
    },
    {
      "id": "2",
      "name": "Dumbo",
      "species": "Elephant",
      "age": 10,
      "healthStatus": "Excellent",
      "lastCheckup": "2024-02-01",
      "assignedKeeperId": 3,
      "diet": "Herbivore",
      "medicalHistory": []
    }
  ]
}
```
4. Run the JSON server : json-server --watch db.json