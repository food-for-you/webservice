--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = food, pg_catalog;

--
-- Data for Name: restaurant; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY restaurant (rid, name, address, phone, postalcode) FROM stdin;
2	Restaurant2	Rankin	1234467	2k3
1	Restaurant1	Rankin	1234567	2k3
3	Restaurant3	Rankin	9998765	8i8
\.


--
-- Name: restaurant_rid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('restaurant_rid_seq', 56, true);


--
-- PostgreSQL database dump complete
--

