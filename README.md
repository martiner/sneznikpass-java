# SneznikPass Java Client

[![Build Status](https://github.com/martiner/sneznikpass-java/workflows/build/badge.svg)](https://github.com/martiner/sneznikpass-java/actions)

A Java client to SneznikPass REST API.

_Disclaimer: for the case of predictability this library retains the original
naming of the REST API no matter how ridiculous it is._

## Usage

### Dependency

The artifact is published to Maven Central Repository

```xml
<dependency>
	<groupId>cz.geek</groupId>
	<artifactId>sneznikpass-client</artifactId>
	<version>RELEASE</version>
</dependency>
```

### Create client and authenticate
```java
SneznikPassClient client = new SneznikPassClient();
Authentication authentication = client.authenticateUser(new Credentials("em@i.l", "pass"));
```

### Create a new stay
```java
Stay stay = new Stay();
stay.setArrival(new LocalDate(2020, 1, 9));
stay.setDeparture(new LocalDate(2020, 1, 21));
stay.setEmail("em@i.l");
stay.setPhoneNumber("+123 456");
stay.setStatus(CONFIRMED);
stay.setNewsletterAgreement(true);

Guest guest = new Guest();
guest.setName("Karel Novák");
guest.setIdCardNumber("123456");
guest.setIdCardType(OBCANSKY_PRUKAZ);
guest.setNoFeeReason(ZTP);
guest.setDateOfBirth(new LocalDate(1980, 9, 20));
guest.setAddress("Karlova 34, Karlovy Vary");
guest.setState("ČR");
stay.add(guest);

CreatedStay createdStay = client.newStay(authentication, stay);
createdStay.getId(); // new id
```

### Update an existing stay by its id
```java
client.updateStay(authentication, "stayid", stay);
```

### List all stays
```java
ListStays list = client.listStays(authentication);
```

### Ping REST service
```java
client.ping();
```

### Error handling
If anything goes wrong the library throws `SneznikPassClientException`.
