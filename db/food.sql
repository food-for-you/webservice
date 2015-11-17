--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: food; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA food;


ALTER SCHEMA food OWNER TO postgres;

SET search_path = food, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: client; Type: TABLE; Schema: food; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    cid integer NOT NULL,
    name character varying(50),
    credential character varying(50),
    email character varying(50),
    phone character varying(20)
);


ALTER TABLE food.client OWNER TO postgres;

--
-- Name: client_cid_seq; Type: SEQUENCE; Schema: food; Owner: postgres
--

CREATE SEQUENCE client_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE food.client_cid_seq OWNER TO postgres;

--
-- Name: client_cid_seq; Type: SEQUENCE OWNED BY; Schema: food; Owner: postgres
--

ALTER SEQUENCE client_cid_seq OWNED BY client.cid;


--
-- Name: menu; Type: TABLE; Schema: food; Owner: postgres; Tablespace: 
--

CREATE TABLE menu (
    mid integer NOT NULL,
    name character varying(50),
    price double precision,
    rid integer,
    image character varying(20)
);


ALTER TABLE food.menu OWNER TO postgres;

--
-- Name: menu_mid_seq; Type: SEQUENCE; Schema: food; Owner: postgres
--

CREATE SEQUENCE menu_mid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE food.menu_mid_seq OWNER TO postgres;

--
-- Name: menu_mid_seq; Type: SEQUENCE OWNED BY; Schema: food; Owner: postgres
--

ALTER SEQUENCE menu_mid_seq OWNED BY menu.mid;


--
-- Name: restaurant; Type: TABLE; Schema: food; Owner: postgres; Tablespace: 
--

CREATE TABLE restaurant (
    rid integer NOT NULL,
    name character varying(50),
    address character varying(50),
    phone character varying(20),
    postalcode character varying(10),
    image character varying(20)
);


ALTER TABLE food.restaurant OWNER TO postgres;

--
-- Name: restaurant_rid_seq; Type: SEQUENCE; Schema: food; Owner: postgres
--

CREATE SEQUENCE restaurant_rid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE food.restaurant_rid_seq OWNER TO postgres;

--
-- Name: restaurant_rid_seq; Type: SEQUENCE OWNED BY; Schema: food; Owner: postgres
--

ALTER SEQUENCE restaurant_rid_seq OWNED BY restaurant.rid;


--
-- Name: tag; Type: TABLE; Schema: food; Owner: postgres; Tablespace: 
--

CREATE TABLE tag (
    tid integer NOT NULL,
    name character varying(20)
);


ALTER TABLE food.tag OWNER TO postgres;

--
-- Name: tag_tid_seq; Type: SEQUENCE; Schema: food; Owner: postgres
--

CREATE SEQUENCE tag_tid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE food.tag_tid_seq OWNER TO postgres;

--
-- Name: tag_tid_seq; Type: SEQUENCE OWNED BY; Schema: food; Owner: postgres
--

ALTER SEQUENCE tag_tid_seq OWNED BY tag.tid;


--
-- Name: tagging; Type: TABLE; Schema: food; Owner: postgres; Tablespace: 
--

CREATE TABLE tagging (
    gid bigint NOT NULL,
    tid integer,
    mid integer,
    rid integer,
    weight integer DEFAULT 1,
    cid integer
);


ALTER TABLE food.tagging OWNER TO postgres;

--
-- Name: tagging_gid_seq; Type: SEQUENCE; Schema: food; Owner: postgres
--

CREATE SEQUENCE tagging_gid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE food.tagging_gid_seq OWNER TO postgres;

--
-- Name: tagging_gid_seq; Type: SEQUENCE OWNED BY; Schema: food; Owner: postgres
--

ALTER SEQUENCE tagging_gid_seq OWNED BY tagging.gid;


--
-- Name: cid; Type: DEFAULT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN cid SET DEFAULT nextval('client_cid_seq'::regclass);


--
-- Name: mid; Type: DEFAULT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY menu ALTER COLUMN mid SET DEFAULT nextval('menu_mid_seq'::regclass);


--
-- Name: rid; Type: DEFAULT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY restaurant ALTER COLUMN rid SET DEFAULT nextval('restaurant_rid_seq'::regclass);


--
-- Name: tid; Type: DEFAULT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY tag ALTER COLUMN tid SET DEFAULT nextval('tag_tid_seq'::regclass);


--
-- Name: gid; Type: DEFAULT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY tagging ALTER COLUMN gid SET DEFAULT nextval('tagging_gid_seq'::regclass);


--
-- Data for Name: client; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY client (cid, name, credential, email, phone) FROM stdin;
\.


--
-- Name: client_cid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('client_cid_seq', 334, true);


--
-- Data for Name: menu; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY menu (mid, name, price, rid, image) FROM stdin;
35	BeefBurger	10	1	Burger1.png
36	ChikenBurger	10	1	Burger2.png
37	BeefBurger_Combo	10	1	Burger3.png
38	BigMac	10	1	Burger5.png
39	BeaconBurger	10	1	Burger6.png
40	Poutine	10	1	Burger7.png
41	ChickenWings	10	1	Chickenwings.png
42	CheeseBurger	10	1	Burger10.png
43	Curry_Shrimp_Noodles	10	1	Gw35Cj.png
44	DuckNoodles	10	2	wWbyPf.png
45	HotFiash	10	2	m7Ox9f.png
46	Huiguorou	10	2	sERJWG.jpeg
47	JapaneseBeefHotpot	10	2	BibmYC.png
48	KoreanFriedChiken	10	2	9GTO6O.png
49	Korean_ZhaJiangMian	10	2	ZJejGo.png
50	Mushroom_Chiken	10	2	daAd0l.png
51	OctopusNoodles	10	3	T9bF6R.png
52	Pancake	10	3	Gr17xM.png
53	Pizza	10	3	GTfKc3.png
54	Sandwish	10	3	T3shHI.png
55	Sashimi	10	3	wM4WMu.png
56	SeasonalBean	10	3	G8pJYn.png
57	SpecyTOFU	10	3	nGeo7Y.png
58	Sushi	10	3	BnrHVS.png
59	SweetSourChiken	10	3	S3EOSq.png
60	ThaiNoodles	10	3	vWkJRk.png
2	OnionRoll	6	1	OnionRoll9.jpg
3	Egg & salad	8	3	bf3.jpg
4	Beef bread	10	2	bf4.jpg
5	Cheese egg	10	2	bf5.jpg
7	Tomato fried egg	10	3	bf7.jpg
6	Silk	30	3	bf6.jpg
1	Bacon fried egg	5	1	bf1.jpg
8	Good morning	10	1	bf8.jpeg
\.


--
-- Name: menu_mid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('menu_mid_seq', 586, true);


--
-- Data for Name: restaurant; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY restaurant (rid, name, address, phone, postalcode, image) FROM stdin;
1	Harveys	2380_Wyandotte_Street	12045099864	N9C2M3	harveys.png
2	SaigonHouse	2169_Wyandotte_Street	12045099433	N9C2M3	saigonhouse.png
3	Seoul_Resaurant	2050_Wyandotte_Street	15195099433	N9C2M3	seoul.png
\.


--
-- Name: restaurant_rid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('restaurant_rid_seq', 671, true);


--
-- Data for Name: tag; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY tag (tid, name) FROM stdin;
1	breakfast
2	lunch
3	dinner
\.


--
-- Name: tag_tid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('tag_tid_seq', 472, true);


--
-- Data for Name: tagging; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY tagging (gid, tid, mid, rid, weight, cid) FROM stdin;
36	2	38	\N	0	\N
37	2	39	\N	0	\N
38	2	46	\N	0	\N
39	2	47	\N	0	\N
40	2	54	\N	0	\N
41	2	55	\N	0	\N
42	3	40	\N	0	\N
43	3	41	\N	0	\N
44	3	42	\N	0	\N
45	3	43	\N	0	\N
46	3	48	\N	0	\N
47	3	49	\N	0	\N
48	3	50	\N	0	\N
49	3	56	\N	0	\N
50	3	57	\N	0	\N
51	3	58	\N	0	\N
52	3	59	\N	0	\N
53	3	60	\N	0	\N
1	1	1	\N	0	\N
2	1	2	\N	0	\N
3	1	3	\N	0	\N
4	1	4	\N	0	\N
5	1	5	\N	0	\N
6	1	6	\N	0	\N
7	1	7	\N	0	\N
8	1	8	\N	0	\N
28	2	35	\N	0	\N
29	2	36	\N	0	\N
30	2	37	\N	0	\N
31	3	44	\N	0	\N
32	3	45	\N	0	\N
33	3	51	\N	0	\N
34	3	52	\N	0	\N
35	3	53	\N	0	\N
\.


--
-- Name: tagging_gid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('tagging_gid_seq', 293, true);


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: food; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (cid);


--
-- Name: menu_pkey; Type: CONSTRAINT; Schema: food; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (mid);


--
-- Name: restaurant_pkey; Type: CONSTRAINT; Schema: food; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (rid);


--
-- Name: tag_pkey; Type: CONSTRAINT; Schema: food; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tid);


--
-- Name: tagging_pkey; Type: CONSTRAINT; Schema: food; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tagging
    ADD CONSTRAINT tagging_pkey PRIMARY KEY (gid);


--
-- Name: menu_rid_fkey; Type: FK CONSTRAINT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_rid_fkey FOREIGN KEY (rid) REFERENCES restaurant(rid);


--
-- Name: tagging_cid_fkey; Type: FK CONSTRAINT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY tagging
    ADD CONSTRAINT tagging_cid_fkey FOREIGN KEY (cid) REFERENCES client(cid);


--
-- Name: tagging_mid_fkey; Type: FK CONSTRAINT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY tagging
    ADD CONSTRAINT tagging_mid_fkey FOREIGN KEY (mid) REFERENCES menu(mid);


--
-- Name: tagging_rid_fkey; Type: FK CONSTRAINT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY tagging
    ADD CONSTRAINT tagging_rid_fkey FOREIGN KEY (rid) REFERENCES restaurant(rid);


--
-- Name: tagging_tid_fkey; Type: FK CONSTRAINT; Schema: food; Owner: postgres
--

ALTER TABLE ONLY tagging
    ADD CONSTRAINT tagging_tid_fkey FOREIGN KEY (tid) REFERENCES tag(tid);


--
-- PostgreSQL database dump complete
--

