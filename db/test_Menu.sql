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
-- Data for Name: menu; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY menu (mid, name, price, rid, image) FROM stdin;
11	pizza	2.99000000000000021	1	pizza.png
12	sushi	3.99000000000000021	1	sushi.png
21	noodles	6.99000000000000021	2	noodles.png
\.


--
-- Name: menu_mid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('menu_mid_seq', 26, true);


--
-- PostgreSQL database dump complete
--

