--
--
-- Copyright 2016, Sayantan Ghosh, and/or his affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements

-- member table should contain member details and their respective roles
-- Table: public.member

-- DROP TABLE public.member;

CREATE TABLE public.member
(
  id bigint NOT NULL,
  name character varying(25) NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  phone_number character varying(12) NOT NULL,
  is_enabled boolean,
  role_as character varying(255),
  CONSTRAINT member_pkey PRIMARY KEY (id),
  CONSTRAINT ukmbmcqelty0fbrvxp1q58dn57t UNIQUE (email)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.member
  OWNER TO "adminJHD5464";

-- software_inventory table to contain software details on user desktops
-- Table: public.software_inventory

-- DROP TABLE public.software_inventory;

CREATE TABLE public.software_inventory
(
  aud_sr_no bigint NOT NULL,
  fk_softwareaudit bigint,
  installed_software character varying(255),
  CONSTRAINT software_inventory_pkey PRIMARY KEY (aud_sr_no)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.software_inventory
  OWNER TO "adminJHD5464";

-- bandwidth table to contain internet and lease line details
-- Table: public.bandwidth

-- DROP TABLE public.bandwidth;

CREATE TABLE public.bandwidth
(
  bandwidth character varying(255) NOT NULL,
  entity character varying(255) NOT NULL,
  location character varying(255) NOT NULL,
  serv_id bigint NOT NULL,
  service_type character varying(255) NOT NULL,
  vendor character varying(255) NOT NULL,
  renewal_date character varying(100),
  start_time character varying(100),
  CONSTRAINT bandwidth_pkey PRIMARY KEY (bandwidth, entity, location, serv_id, service_type, vendor)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.bandwidth
  OWNER TO "adminJHD5464";

-- inventory_mgmt table to contain user desktop inventory
-- Table: public.inventory_mgmt

-- DROP TABLE public.inventory_mgmt;

CREATE TABLE public.inventory_mgmt
(
  sr_no bigint NOT NULL,
  user_id character varying(255) NOT NULL,
  login_name character varying(25),
  domain character varying(12) NOT NULL,
  service_tag character varying(255),
  machine character varying(255),
  model character varying(255),
  processor character varying(255),
  ram_gb character varying(255),
  hdd_gb character varying(255),
  operating_system character varying(255),
  open_office character varying(255),
  ms_office_version character varying(255),
  mcafee_version character varying(255),
  ip_add character varying(255),
  os_serial character varying(255),
  last_check character varying(255),
  CONSTRAINT inventory_mgmt_pkey PRIMARY KEY (sr_no)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.inventory_mgmt
  OWNER TO "adminJHD5464";

-- licenses table to contain software/hardware/vendor/service license details
-- Table: public.licenses

-- DROP TABLE public.licenses;

CREATE TABLE public.licenses
(
  lic_sr_no bigint NOT NULL,
  invoice_number character varying(255),
  item_number character varying(255),
  serial_number character varying(255),
  curr character varying(255),
  item_price character varying(255),
  amt_payable character varying(255),
  auth_signatory character varying(255),
  tot_qnty integer,
  fk_machinedetails_softwareaudit bigint,
  lob_head character varying(255),
  lob_lead character varying(255),
  login_name character varying(25),
  mail_body character varying(255),
  net_annual_price character varying(255),
  ownership character varying(255),
  paid boolean,
  particulars character varying(255),
  reminder integer,
  renewal_date character varying(255),
  vendor_contact character varying(255),
  vendor_contact_email character varying(255),
  vendor_contact_phone character varying(255),
  src_folder character varying(255),
  CONSTRAINT licenses_pkey PRIMARY KEY (lic_sr_no)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.licenses
  OWNER TO "adminJHD5464";

-- machine_details table to be master table for all machines after establishing relationships
-- Table: public.machine_details

-- DROP TABLE public.machine_details;

CREATE TABLE public.machine_details
(
  mc_sr_no bigint NOT NULL,
  domain character varying(12) NOT NULL,
  ip character varying(255),
  mac_id character varying(25) NOT NULL,
  team character varying(255),
  user_id character varying(255) NOT NULL,
  CONSTRAINT machine_details_pkey PRIMARY KEY (mc_sr_no),
  CONSTRAINT uk1kkm3pssihkcsoui5egrvvd08 UNIQUE (mac_id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.machine_details
  OWNER TO "adminJHD5464";


-- server_inventory table to contain server details
-- Table: public.server_inventory

-- DROP TABLE public.server_inventory;

CREATE TABLE public.server_inventory
(
  serv_sr_no bigint NOT NULL,
  tech_owner character varying(25) NOT NULL,
  lob_name character varying(255),
  env_name character varying(255),
  host_name character varying(255),
  ip_add character varying(255) NOT NULL,
  item_make character varying(255),
  item_model character varying(255),
  serial_no character varying(255),
  purchase_date character varying(255),
  processor character varying(255),
  proc_no character varying(255),
  core_no character varying(255),
  tot_core character varying(255),
  ram character varying(255),
  ram_type character varying(255),
  freq_mhz character varying(255),
  hdd character varying(255),
  platform character varying(255),
  os character varying(255),
  entity character varying(255),
  server_unit character varying(255),
  rack character varying(255),
  rack_unit character varying(255),
  fk_MachineDetails_SoftwareAudit bigint,
  role_name character varying(255),
  location character varying(255),
  CONSTRAINT server_inventory_pkey PRIMARY KEY (serv_sr_no),
  CONSTRAINT uk1t73u0op9bdh8g3j6ax03iolc UNIQUE (ip_add)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.server_inventory
  OWNER TO "adminJHD5464";

