// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract pollContract {
    struct Poll {
        uint256 _pollId; // this will be matched with the firebase realtime db
        string _agendaOfPoll;
        address _owner; // this will be the address from where the poll creation function is called
        uint256 _startTime;
        uint256 _endTime;
        uint8 _age;
        bool _isPoll;
    }
    mapping(uint256 => mapping(uint256 => address)) public VotersWhoHaveVoted;
    //      (_pollId) => ( aadharNo => address)
    // this is mapping of all the pollId to another mapping of all the aadharNo of Voters
    // to public address of their account

    mapping(uint256 => Candidate[]) public ListOfCandidate;
    //      (PollId => ListOfCandidate)

    struct Candidate {
        address _candidate; // this is the address of the account where participate_in_a_poll method is called
        uint256 _aadharNo; // this is the aadhar no of the candidate
        uint256 _pollId;
        string _agenda;
        uint8 _age;
        uint256 _voteCount;
    }

    struct Voter {
        address _voterAddress; // this is the address of the voter that votes in the poll
        uint256 _aadharNo; // this is the aadhar no of the voter and every time a vote is casted , a temp. voter obj. will be created and
        //     the voter will be flaged true whenever he votes
        uint8 _age;
        uint256[] _participatedPoll; // this will have all the pollId that voter has voted for
        uint256[] _upcomingPoll; // this will have all the pollId that voter can vote for
        bool _isRegistered;
    }

    struct Admin {
        address _adminAddress; // this is the address of the voter that votes in the poll
        uint256 _aadharNo; // this is the aadhar no of the voter and every time a vote is casted , a temp. voter obj. will be created and
        //     the voter will be flaged true whenever he votes
        uint8 _age;
        uint256[] _createdPoll; // this will have all the pollId that admin has created
        bool _isRegistered;
    }

    mapping(uint256 => Poll) public AllPolls; // this will store all the polls that are being created
    //  ( _pollId => Poll)       by asceding order of startTime

    mapping(uint256 => Voter) public AllVoter; // this will store all the voters
    // (aadharNo => Voter)
    uint256[] public AllVoterAadhar; // this will store aadhar no of all voters
    mapping(uint256 => Admin) public AllAdmin; // this will store all the Aadmin

    // (aadharNo => Voter)

    function castVote(
        uint256 pollIdInput,
        uint256 candidateAadharInput,
        uint256 voterAadharNoInput
    ) public {
        require(AllPolls[pollIdInput]._isPoll, "There is no such poll");
        require(
            AllVoter[voterAadharNoInput]._isRegistered,
            "You are not registered as a voter"
        );
        require(
            VotersWhoHaveVoted[pollIdInput][voterAadharNoInput] != msg.sender,
            "You have already voted"
        );
        require(
            AllVoter[voterAadharNoInput]._age >= AllPolls[pollIdInput]._age,
            "You are not eligible for voting in this poll"
        );
        require(
            AllPolls[pollIdInput]._startTime <= block.timestamp,
            "The poll has not started"
        );
        require(
            AllPolls[pollIdInput]._endTime >= block.timestamp,
            "The poll has been closed"
        );

        Candidate[] storage candidates = ListOfCandidate[pollIdInput];

        bool candidateFound = false;
        for (uint256 i = 0; i < candidates.length; i++) {
            if (candidates[i]._aadharNo == candidateAadharInput) {
                candidates[i]._voteCount += 1; // Increase the vote count of the candidate
                candidateFound = true;
                break;
            }
        }

        require(candidateFound, "The candidate is not registered");

        VotersWhoHaveVoted[pollIdInput][voterAadharNoInput] = msg.sender; // Mark that the voter has voted
        sortCandidates(pollIdInput); // Sort candidates after vote casting
    }

    function viewPollResult(uint256 pollIdInput)
    public
    view
    returns (Candidate[] memory)
    {
        return ListOfCandidate[pollIdInput];
    }

    function createVoter(uint256 aadharNoInput, uint8 ageInput) public {
        require(ageInput > 0, "The age is invalid");
        require(countDigit(aadharNoInput) == 12, "The Aadhar no is invalid");
        require(
            !AllVoter[aadharNoInput]._isRegistered,
            "The Voter is already registered"
        );
        Voter memory voter = Voter({
            _voterAddress: msg.sender,
            _aadharNo: aadharNoInput,
            _age: ageInput,
            _participatedPoll: new uint256[](0),
            _upcomingPoll: new uint256[](0),
            _isRegistered: true
        });
        AllVoter[aadharNoInput] = voter;
        AllVoterAadhar.push(aadharNoInput);
    }

    // an admin will have both Voter and Admin Account , if you create an Admin directly then
    // it will create both the Voter and Admin account  , but if you registered it after
    // becoming an voter then it will create your admin account , to make this check
    // we will check if the AllVoter has the aadharNo account earlier or not , if it is then
    // he is upgrading the account else he is creating a fresh account

    function createAdmin(uint256 aadharNoInput, uint8 ageInput) public {
        require(ageInput > 0, "The age is invalid");
        require(countDigit(aadharNoInput) == 12, "The Aadhar no is invalid");
        require(
            !AllAdmin[aadharNoInput]._isRegistered,
            "The Admin is already registered"
        );
        if (!AllVoter[aadharNoInput]._isRegistered) {
            // this means the user is new so creating both accounts
            createVoter(aadharNoInput, ageInput);
        }
        Admin memory admin = Admin({
            _adminAddress: msg.sender,
            _aadharNo: aadharNoInput,
            _age: ageInput,
            _createdPoll: new uint256[](0),
            _isRegistered: true
        });
        AllAdmin[aadharNoInput] = admin;
    }

    function deleteAdmin(uint256 aadharNoInput) public {
        require(
            AllAdmin[aadharNoInput]._isRegistered,
            "The user is not an Admin"
        );
        require(
            AllVoter[aadharNoInput]._isRegistered,
            "The user is not an Voter"
        );
        delete AllAdmin[aadharNoInput];
        // here the admin account's all data will be set to 0
        // we also can just make the flag of_isregistered to false , then this will preserve the
        // data , but we are keeping it simple
    }

    function createCandidate(
        uint256 aadharNoInput,
        uint256 pollIdInput,
        string memory agendaInput,
        uint8 ageInput
    ) public {
        require(AllPolls[pollIdInput]._isPoll, "There is no such poll");
        require(
            !isCandidatePresent(ListOfCandidate[pollIdInput], aadharNoInput),
            "The Candidate is already present"
        );
        require(
            block.timestamp < AllPolls[pollIdInput]._startTime,
            "The Poll has been started"
        );
        require(
            ageInput >= AllPolls[pollIdInput]._age,
            "You are smaller then required age"
        );

        Candidate memory candidate = Candidate({
            _candidate: msg.sender,
            _aadharNo: aadharNoInput,
            _pollId: pollIdInput,
            _agenda: agendaInput,
            _voteCount: 0,
            _age: ageInput
        });
        ListOfCandidate[pollIdInput].push(candidate);
    }

    // Function to check if a candidate with a given aadharNo is present in the array
    function isCandidatePresent(
        Candidate[] memory _candidates,
        uint256 aadharNo
    ) public pure returns (bool) {
        for (uint256 i = 0; i < _candidates.length; i++) {
            if (_candidates[i]._aadharNo == aadharNo) {
                return true; // Candidate found
            }
        }
        return false; // Candidate not found
    }

    function createPoll(
        uint256 pollIdInput,
        string memory agendaOfPollInput,
        uint256 startTimeInput,
        uint256 endTimeInput,
        uint8 ageInput,
        uint256 adminAadharNo
    ) public {
        require(
            AllPolls[pollIdInput]._isPoll == false,
            "The Poll Id is already taken by another Poll"
        );
        require(
            startTimeInput < endTimeInput - 300,
            "The Time is either Too small or not valid"
        );
        // this ^ require statement checks that there is atleast 5 minutes gap b/w start and end
        require(ageInput > 0, "Invalid Age");
        require(
            AllAdmin[adminAadharNo]._isRegistered &&
            AllAdmin[adminAadharNo]._adminAddress == msg.sender,
            "There is no such admin or You are not an admin"
        );

        Poll memory poll = Poll({
            _pollId: pollIdInput,
            _age: ageInput,
            _agendaOfPoll: agendaOfPollInput,
            _owner: msg.sender,
            _startTime: startTimeInput,
            _endTime: endTimeInput,
            _isPoll: true
        });
        // inserting in AllPolls
        AllPolls[pollIdInput] = poll;
        // inserting in admin =._createdPoll
        AllAdmin[adminAadharNo]._createdPoll.push(pollIdInput);
        ListOfCandidate[pollIdInput];

        //inserting into Voter.upcomingPoll if age>=ageOFThePoll
        for (uint256 i = 0; i < AllVoterAadhar.length; i++) {
            if (AllVoter[AllVoterAadhar[i]]._age >= ageInput) {
                AllVoter[AllVoterAadhar[i]]._upcomingPoll.push(pollIdInput);
            }
        }
    }

    //

    // Function to sort candidates in descending order based on vote count
    function sortCandidates(uint256 pollIdInput) public {
        Candidate[] storage candidates = ListOfCandidate[pollIdInput];
        quickSort(candidates, 0, int256(candidates.length - 1));
    }

    // QuickSort algorithm to sort candidates in descending order
    function quickSort(
        Candidate[] storage arr,
        int256 left,
        int256 right
    ) internal {
        if (left < right) {
            int256 pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    // Partition function used by QuickSort
    function partition(
        Candidate[] storage arr,
        int256 left,
        int256 right
    ) internal returns (int256) {
        uint256 pivot = arr[uint256(right)]._voteCount;
        int256 i = left - 1;
        for (int256 j = left; j < right; j++) {
            if (arr[uint256(j)]._voteCount > pivot) {
                // Descending order
                i++;
                // Swap arr[i] and arr[j]
                Candidate memory temp = arr[uint256(i)];
                arr[uint256(i)] = arr[uint256(j)];
                arr[uint256(j)] = temp;
            }
        }
        // Swap arr[i+1] and arr[right] (pivot)
        Candidate memory temp1 = arr[uint256(i + 1)];
        arr[uint256(i + 1)] = arr[uint256(right)];
        arr[uint256(right)] = temp1;
        return i + 1;
    }

    // Function to get the sorted array of candidates
    function getCandidates(uint256 pollIdInput)
    public
    view
    returns (Candidate[] memory)
    {
        return ListOfCandidate[pollIdInput];
    }

    //

    function countDigit(uint256 number) public pure returns (uint8) {
        if (number == 0) {
            return 1;
        }
        uint8 count = 0;
        while (number != 0) {
            number = number / 10;
            count++;
        }
        return count;
    }
}
