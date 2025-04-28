--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-04-25 21:24:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4930 (class 0 OID 16649)
-- Dependencies: 218
-- Data for Name: registro_cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.registro_cliente (id, nombre, apellido, cedula, correo, clave) FROM stdin;
1	Carlos	Ramírez	12345678	carlos.ramirez@example.com	miclave123
\.


--
-- TOC entry 4932 (class 0 OID 16661)
-- Dependencies: 220
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nombre, apellido, correo, telefono, tipo_cliente, registro_id) FROM stdin;
\.


--
-- TOC entry 4934 (class 0 OID 16676)
-- Dependencies: 222
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productos (id, nombre, descripcion, precio, stock, categoria) FROM stdin;
\.


--
-- TOC entry 4936 (class 0 OID 16685)
-- Dependencies: 224
-- Data for Name: carrito; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.carrito (id, cliente_id, producto_id, cantidad, fecha_agregado) FROM stdin;
\.


--
-- TOC entry 4938 (class 0 OID 16703)
-- Dependencies: 226
-- Data for Name: compras; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.compras (id, cliente_id, producto_id, cantidad, total, fecha_compra) FROM stdin;
\.


--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 223
-- Name: carrito_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carrito_id_seq', 1, false);


--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 219
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 1, false);


--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 225
-- Name: compras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compras_id_seq', 1, false);


--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productos_id_seq', 1, false);


--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 217
-- Name: registro_cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.registro_cliente_id_seq', 1, true);


-- Completed on 2025-04-25 21:24:54

--
-- PostgreSQL database dump complete
--

