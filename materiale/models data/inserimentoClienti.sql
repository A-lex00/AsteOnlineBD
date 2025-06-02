/*INSERT INTO cliente (nome, cognome, CF, dataNascita, cittàNascita, passwordHash, numeroCarta, CVV, dataScadenza, via, città, CAP)
VALUES 
('Andrea', 'Bianchi', 'BNCDRA85M01H501J', '1985-03-15', 'Milano', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd9b31c3c227f9a1f5a', '4539589763621487', '321', '2026-08-31', 'Via Milano 12', 'Milano', '20100'),
('Federica', 'Rossi', 'RSSFRC90C45F205H', '1990-12-05', 'Roma', '6b3a55e0261b0304143f805a2495b007d7e2044c6d42d5c17ca7b1f911972f66', '4716123456789012', '654', '2025-11-30', 'Corso Trieste 34', 'Roma', '00198'),
('Lorenzo', 'Verdi', 'VRDLNZ75T12L219M', '1975-07-20', 'Torino', 'f4a3db3df57d8b5ae11c45f6dbb4305f33e6a59f15a53fdb0b6bda3424c5fb82', '4485275742308327', '987', '2024-09-15', 'Piazza Castello 8', 'Torino', '10122'),
('Elisa', 'Neri', 'NRIELS82L29H501D', '1982-11-29', 'Firenze', '3c6e0b8a9c15224a8228b9a98ca1531d5c2986b744b7ffefc1e28d8149f7b161', '4929357628311564', '111', '2027-07-31', 'Viale Europa 19', 'Firenze', '50126'),
('Matteo', 'Galli', 'GLLMTT88S15H501W', '1988-06-15', 'Napoli', 'b2a5cd8cb71319dbdd6525c67a4654e85a64f91c2f2113e15752ee728cd77852', '4556737586899855', '222', '2025-06-30', 'Via Toledo 23', 'Napoli', '80100');

INSERT INTO CATEGORIA (nomeCategoria,categoriaSuperiore)
VALUES
('Elettronica', NULL),
('Arte', NULL),
('Gioielli', NULL),
('Collezionismo', NULL),
('Veicoli', NULL),
('Immobili', NULL),
('Laptop', 'Elettronica'),
('Fotocamere', 'Elettronica'),
('Pittura', 'Arte'),
('Scultura', 'Arte'),
('Antiquariato', 'Arte'),
('Anelli', 'Gioielli'),
('Collane', 'Gioielli'),
('Orologi', 'Gioielli'),
('Monete', 'Collezionismo'),
('Francobolli', 'Collezionismo'),
('Auto d''epoca', 'Veicoli'),
('Moto', 'Veicoli'),
('iPhone', 'Smartphone'),
('Android', 'Smartphone'),
('Windows Laptop', 'Laptop'),
('MacBook', 'Laptop'),
('Fotocamere DSLR', 'Fotocamere'),
('Fotocamere Mirrorless', 'Fotocamere'),
('Impressionismo', 'Pittura'),
('Astratto', 'Pittura'),
('Sculture in Bronzo', 'Scultura'),
('Orologi da Polso', 'Orologi'),
('Orologi da Tasca', 'Orologi'),
('Monete Romane', 'Monete'),
('Monete Greche', 'Monete');
*/
INSERT INTO oggetto_in_vendita(codice, descrizione, prezzoBase, Altezza, Lunghezza, Larghezza, stato, categoria, proprietario, offertaMassima) VALUES
('ELT-001', 'Smartphone di ultima generazione, colore nero, 128GB di memoria.', 500.00, 0.15, 0.07, 0.008, 'come nuovo', 'Android', NULL, NULL);


