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
    postalcode character varying(10)
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

SELECT pg_catalog.setval('client_cid_seq', 47, true);


--
-- Data for Name: menu; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY menu (mid, name, price, rid, image) FROM stdin;
35	Beans	10	\N	XsjNbR.png
36	braised_pork_chops	10	\N	RAW5o5.png
37	Burger	10	\N	B6LI30.png
38	Chaomian_ChikenShrimp	10	\N	ZZLuiu.png
39	ChikenFeet	10	\N	NNO7Dt.png
40	ChikenPotato	10	\N	OKKxBG.png
41	ChikenSausage	10	\N	XEN7qs.png
42	CurryBeef	10	\N	mOYbfO.png
43	Curry_Shrimp_Noodles	10	\N	Gw35Cj.png
44	DuckNoodles	10	\N	wWbyPf.png
45	HotFiash	10	\N	m7Ox9f.png
46	Huiguorou	10	\N	sERJWG.jpeg
47	JapaneseBeefHotpot	10	\N	BibmYC.png
48	KoreanFriedChiken	10	\N	9GTO6O.png
49	Korean_ZhaJiangMian	10	\N	ZJejGo.png
50	Mushroom_Chiken	10	\N	daAd0l.png
51	OctopusNoodles	10	\N	T9bF6R.png
52	Pancake	10	\N	Gr17xM.png
53	Pizza	10	\N	GTfKc3.png
54	Sandwish	10	\N	T3shHI.png
55	Sashimi	10	\N	wM4WMu.png
56	SeasonalBean	10	\N	G8pJYn.png
57	SpecyTOFU	10	\N	nGeo7Y.png
58	Sushi	10	\N	BnrHVS.png
59	SweetSourChiken	10	\N	S3EOSq.png
60	ThaiNoodles	10	\N	vWkJRk.png
\.


--
-- Name: menu_mid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('menu_mid_seq', 60, true);


--
-- Data for Name: restaurant; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY restaurant (rid, name, address, phone, postalcode) FROM stdin;
\.


--
-- Name: restaurant_rid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('restaurant_rid_seq', 48, true);


--
-- Data for Name: tag; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY tag (tid, name) FROM stdin;
\.


--
-- Name: tag_tid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('tag_tid_seq', 42, true);


--
-- Data for Name: tagging; Type: TABLE DATA; Schema: food; Owner: postgres
--

COPY tagging (gid, tid, mid, rid, weight, cid) FROM stdin;
\.


--
-- Name: tagging_gid_seq; Type: SEQUENCE SET; Schema: food; Owner: postgres
--

SELECT pg_catalog.setval('tagging_gid_seq', 16, true);


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

