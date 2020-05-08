--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12
-- Dumped by pg_dump version 12rc1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: AnaAgSistemi; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "AnaAgSistemi" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Turkish_Turkey.1254' LC_CTYPE = 'Turkish_Turkey.1254';


ALTER DATABASE "AnaAgSistemi" OWNER TO postgres;

\connect "AnaAgSistemi"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- Name: akillicihaz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.akillicihaz (
    id character varying(6) NOT NULL,
    sicaklik_degeri real NOT NULL,
    durum character varying(8)
);


ALTER TABLE public.akillicihaz OWNER TO postgres;

--
-- Name: kullanicihesabi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kullanicihesabi (
    id integer NOT NULL,
    kullanici_adi character varying(6) NOT NULL,
    sifre character varying(18) NOT NULL
);


ALTER TABLE public.kullanicihesabi OWNER TO postgres;

--
-- Name: kullanicihesabi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kullanicihesabi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kullanicihesabi_id_seq OWNER TO postgres;

--
-- Name: kullanicihesabi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kullanicihesabi_id_seq OWNED BY public.kullanicihesabi.id;


--
-- Name: musteri; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.musteri (
    id integer NOT NULL,
    ad character varying(40) NOT NULL,
    soyad character varying(40) NOT NULL
);


ALTER TABLE public.musteri OWNER TO postgres;

--
-- Name: kullanicihesabi id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanicihesabi ALTER COLUMN id SET DEFAULT nextval('public.kullanicihesabi_id_seq'::regclass);


--
-- Data for Name: akillicihaz; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.akillicihaz VALUES ('M10001', 1, 'kontrol');
INSERT INTO public.akillicihaz VALUES ('M10002', -7, 'kapali');
INSERT INTO public.akillicihaz VALUES ('M10000', 14, 'kapali');
INSERT INTO public.akillicihaz VALUES ('M10003', 7, 'kapali');


--
-- Data for Name: kullanicihesabi; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.kullanicihesabi VALUES (1, 'M10000', 'password123');
INSERT INTO public.kullanicihesabi VALUES (2, 'M10001', 'qwerty');
INSERT INTO public.kullanicihesabi VALUES (3, 'M10002', '123456');
INSERT INTO public.kullanicihesabi VALUES (5, 'M10003', '123456Seven');


--
-- Data for Name: musteri; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.musteri VALUES (1, 'Ahmet', 'Öztürk');
INSERT INTO public.musteri VALUES (2, 'Tuna', 'Akşan');
INSERT INTO public.musteri VALUES (3, 'Mehmet', 'Demirci');
INSERT INTO public.musteri VALUES (5, 'Erkan', 'Ballı');


--
-- Name: kullanicihesabi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kullanicihesabi_id_seq', 5, true);


--
-- Name: akillicihaz akillicihaz_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.akillicihaz
    ADD CONSTRAINT akillicihaz_pkey PRIMARY KEY (id);


--
-- Name: kullanicihesabi kullanicihesabi_kullanici_adi_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanicihesabi
    ADD CONSTRAINT kullanicihesabi_kullanici_adi_key UNIQUE (kullanici_adi);


--
-- Name: kullanicihesabi kullanicihesabi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanicihesabi
    ADD CONSTRAINT kullanicihesabi_pkey PRIMARY KEY (id);


--
-- Name: musteri musteri_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musteri
    ADD CONSTRAINT musteri_pkey PRIMARY KEY (id);


--
-- Name: musteri musteri_fk_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musteri
    ADD CONSTRAINT musteri_fk_id FOREIGN KEY (id) REFERENCES public.kullanicihesabi(id);


--
-- PostgreSQL database dump complete
--

