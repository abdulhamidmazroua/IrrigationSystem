create database if not exists irrigation_directory;
use irrigation_directory;

-- Table for time slots
drop table if exists time_slots;
CREATE TABLE time_slots (
  id INT PRIMARY KEY AUTO_INCREMENT,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  status VARCHAR(20) NOT NULL
) engine=InnoDB auto_increment=1 default charset=latin1;

-- Table for plots configuration
drop table if exists plot_configuration;
CREATE TABLE plot_configuration (
  id INT PRIMARY KEY AUTO_INCREMENT,
  water_required DECIMAL(10, 2) NOT NULL,
  time_slot_id INT,
  FOREIGN KEY (time_slot_id) REFERENCES time_slots(id)
) engine=InnoDB auto_increment=1 default charset=latin1;

-- Table for plots of land
drop table if exists plots;
CREATE TABLE plots (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  area DECIMAL(10, 2) NOT NULL,
  configuration_id INT,
  foreign key (configuration_id) REFERENCES plot_configuration (id)
) engine=InnoDB auto_increment=1 default charset=latin1;

-- Table for sensor devices
drop table if exists sensor_devices;
CREATE TABLE sensor_devices (
  id INT PRIMARY KEY AUTO_INCREMENT,
  plot_id INT,
  status VARCHAR(20) NOT NULL,
  FOREIGN KEY (plot_id) REFERENCES plots(id)
) engine=InnoDB auto_increment=1 default charset=latin1;

-- Insert seed data for time_slots
INSERT INTO time_slots (start_time, end_time, status) VALUES
('08:00:00', '10:00:00', 'Reserved'),
('10:00:00', '12:00:00', 'Reserved'),
('12:00:00', '14:00:00', 'Reserved');

-- Insert seed data for plot_configuration
INSERT INTO plot_configuration (time_slot_id, water_required) VALUES
(1, 200),
(2, 300),
(3, 400);

-- Insert seed data for plots
INSERT INTO plots (name, area, configuration_id) VALUES
('Plot A', 1000.50, 1),
('Plot B', 1500.75, 2),
('Plot C', 800.25, 3);

-- Insert seed data for sensor_devices
INSERT INTO sensor_devices (plot_id, status) VALUES
(1, 'Active'),
(2, 'Inactive'),
(3, 'Active');
